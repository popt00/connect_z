package ca.parimal.connectz.model.dao.entites.trash;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MediaKey implements Serializable {
    @Column(name = "media_id")
    private Integer mediaId;

    public MediaKey(Integer mediaId) {
        this.mediaId = mediaId;
    }
    public MediaKey() {}

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaKey mediaKey = (MediaKey) o;
        return Objects.equals(mediaId, mediaKey.mediaId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mediaId);
    }
}
