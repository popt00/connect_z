package ca.parimal.connectz.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;

@Embeddable
public class EntryKey implements Serializable {

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "media_id")
    Integer mediaId;

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
        if(this.mediaId == o.getMediaId() &&  this.userId== o.getMediaId())return true;
        return false;
    }
}
