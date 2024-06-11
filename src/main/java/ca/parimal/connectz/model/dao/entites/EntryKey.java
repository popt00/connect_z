package ca.parimal.connectz.model.dao.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;

@Embeddable
public class EntryKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "media_id")
    private Integer mediaId;

    public EntryKey() {}

    public EntryKey(Long userId, Integer mediaId) {
        this.userId = userId;
        this.mediaId = mediaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null)return false;

        Class<?> objEffectiveClass = (
                ( obj instanceof HibernateProxy ) ? ((HibernateProxy)obj).getHibernateLazyInitializer().getPersistentClass() : obj.getClass()
        );
        Class<?> thisEffectiveClass = (
                ( this instanceof HibernateProxy ) ? ((HibernateProxy)this).getHibernateLazyInitializer().getPersistentClass() : this.getClass()
        );
        if(objEffectiveClass != thisEffectiveClass)return false;
        EntryKey o = (EntryKey) obj;
        if(this.mediaId == o.getMediaId() &&  this.userId== o.getUserId())return true;
        return false;
    }
}
