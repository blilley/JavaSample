package net.gartee.openperiodical.core.identities;

import java.util.UUID;

public class ContentId {
    private UUID id;

    public ContentId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;

        if(!(o instanceof ContentId))
            return false;

        ContentId contentId = (ContentId) o;

        return getId() == contentId.getId();
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }
}