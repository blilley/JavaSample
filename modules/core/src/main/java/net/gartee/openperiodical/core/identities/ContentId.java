package net.gartee.openperiodical.core.identities;

public class ContentId {
    private int id;

    public ContentId(int id) {
        this.id = id;
    }

    public int getId() {
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