package ca.parimal.connectz.model.dao.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EntryKey implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "media_id")
    private Integer mediaId;

    public EntryKey() {}

    public EntryKey(Integer userId, Integer mediaId) {
        this.userId = userId;
        this.mediaId = mediaId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
        EntryKey entryKey = (EntryKey) o;
        return Objects.equals(userId, entryKey.userId) && Objects.equals(mediaId, entryKey.mediaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, mediaId);
    }
}
