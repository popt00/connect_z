package ca.parimal.connectz.model.entities.dao;

import jakarta.persistence.*;
import org.json.simple.JSONObject;

@Entity(name = "media")
public class Media {
    public static final String QUERY="media{id, title {romaji}}";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    public Long mediaId;

    @Column(name = "anilist_media_id")
    public int anilistMediaId;

    @Column(name = "title")
    public String title;

//    @OneToMany(mappedBy = "media")
//    Set<Entry> entries;

    public Media(){}

    public Media(int anilistMediaId, String title) {
        this.anilistMediaId = anilistMediaId;
        this.title = title;
    }

    public Media (JSONObject obj){
        anilistMediaId = Integer.parseInt(obj.get("id").toString());
        title = ((JSONObject)obj.get("title")).get("romaji").toString();
    }

    @Override
    public String toString() {
        return "Media{" +
                "anilistId=" + anilistMediaId +
                ", title='" + title + '\'' +
                '}';
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnilistMediaId() {
        return anilistMediaId;
    }

    public void setAnilistMediaId(int anilistId) {
        this.anilistMediaId = anilistId;
    }
}
