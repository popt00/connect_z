package ca.parimal.connectz.model.dao.entites;

import jakarta.persistence.*;

@Entity(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Long mediaId;

    @Column(name = "anilist_media_id")
    private int anilistMediaId;

    @Column(name = "title")
    private String title;

//    @OneToMany(mappedBy = "media")
//    Set<Entry> entries;

    public Media(){}

    public Media(int anilistMediaId, String title) {
        this.anilistMediaId = anilistMediaId;
        this.title = title;
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
