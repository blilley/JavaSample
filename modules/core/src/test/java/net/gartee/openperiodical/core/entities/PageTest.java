package net.gartee.openperiodical.core.entities;

import net.gartee.openperiodical.core.builders.PageBuilder;
import net.gartee.openperiodical.core.exceptions.ContentExceedsAvailableSizeException;
import net.gartee.openperiodical.core.identities.ContentId;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class PageTest {
    private static final String PUBLICATION_DATE = "1/1/2001";
    private static final int PAGE_ONE = 1;
    private static final boolean IS_FIXED_SIZE = true;
    private static final boolean IS_NOT_FIXED_SIZE = false;

    @Test
    public void addContent_WithSingleContentThatFitsPageSize_AddsContent() {
        Page page = PageBuilder.create()
            .withPeriodicalId(UUID.randomUUID())
            .withPublicationDate(PUBLICATION_DATE)
            .withPageNumber(PAGE_ONE)
            .withHeight(2)
            .withWidth(2)
            .build();
        FakeContent content = buildFakeContent(IS_FIXED_SIZE);
        content.setHeight(1);
        content.setWidth(1);

        page.addContent(content);

        assertThat(page.getContents(), hasItem(content));
    }

    @Test(expected = ContentExceedsAvailableSizeException.class)
    public void addContent_WithSingleContentThatIsBiggerThanPageSize_ThrowsException() {
        Page page = PageBuilder.create()
                .withPeriodicalId(UUID.randomUUID())
                .withPublicationDate(PUBLICATION_DATE)
                .withPageNumber(PAGE_ONE)
                .withHeight(1)
                .withWidth(1)
                .build();

        FakeContent content = buildFakeContent(IS_FIXED_SIZE);
        content.setHeight(2);
        content.setWidth(2);

        page.addContent(content);
    }

    @Test(expected = ContentExceedsAvailableSizeException.class)
    public void addContent_WithSingleContentThatIsWiderThanPageWidth_ThrowsException() {
        Page page = PageBuilder.create()
                .withPeriodicalId(UUID.randomUUID())
                .withPublicationDate(PUBLICATION_DATE)
                .withPageNumber(PAGE_ONE)
                .withHeight(4)
                .withWidth(4)
                .build();

        FakeContent content = buildFakeContent(IS_FIXED_SIZE);
        content.setHeight(1);
        content.setWidth(5);

        page.addContent(content);
    }

    @Test(expected = ContentExceedsAvailableSizeException.class)
    public void addContent_WithSingleContentThatIsTallerThanPageHeight_ThrowsException() {
        Page page = PageBuilder.create()
                .withPeriodicalId(UUID.randomUUID())
                .withPublicationDate(PUBLICATION_DATE)
                .withPageNumber(PAGE_ONE)
                .withHeight(4)
                .withWidth(4)
                .build();

        FakeContent content = buildFakeContent(IS_FIXED_SIZE);
        content.setHeight(5);
        content.setWidth(1);

        page.addContent(content);
    }

    @Test(expected = ContentExceedsAvailableSizeException.class)
    public void addContent_WithTwoContentsThatTogetherExceedPageSize_ThrowsException() {
        Page page = PageBuilder.create()
                .withPeriodicalId(UUID.randomUUID())
                .withPublicationDate(PUBLICATION_DATE)
                .withPageNumber(PAGE_ONE)
                .withHeight(4)
                .withWidth(4)
                .build();

        FakeContent content1 = buildFakeContent(IS_FIXED_SIZE);
        content1.setHeight(3);
        content1.setWidth(3);

        FakeContent content2 = buildFakeContent(IS_FIXED_SIZE);
        content2.setHeight(3);
        content2.setWidth(3);

        page.addContent(content1);
        page.addContent(content2);
    }

    @Test(expected = ContentExceedsAvailableSizeException.class)
    @Ignore
    public void addContent_WithTwoContentsThatWouldOverlap_ThrowsException() {
        Page page = PageBuilder.create()
                .withPeriodicalId(UUID.randomUUID())
                .withPublicationDate(PUBLICATION_DATE)
                .withPageNumber(PAGE_ONE)
                .withHeight(10)
                .withWidth(10)
                .build();

        FakeContent content1 = buildFakeContent(IS_FIXED_SIZE);
        content1.setHeight(6);
        content1.setWidth(6);

        FakeContent content2 = buildFakeContent(IS_FIXED_SIZE);
        content2.setHeight(6);
        content2.setWidth(6);

        page.addContent(content1);
        page.addContent(content2);
    }

    private FakeContent buildFakeContent(boolean isFixedSize) {
        return new FakeContent(new ContentId(UUID.randomUUID()), isFixedSize);
    }

    private class FakeContent extends Content {
        private final boolean isFixedSize;

        public FakeContent(ContentId id, boolean isFixedSize) {
            super(id);
            this.isFixedSize = isFixedSize;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        @Override
        public boolean isFixedSize() {
            return isFixedSize;
        }
    }
}