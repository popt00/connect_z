package ca.parimal.connectz.controller.dto.entities;

import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.Media;
import ca.parimal.connectz.model.dao.entites.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import org.json.simple.JSONObject;

public class EntryGraphQl {
    private UserGraphql user;
    private MediaGraphQl media;
    private Integer score;
    //range 0-100
    private String status;

    public EntryGraphQl(UserGraphql user, MediaGraphQl media, Integer score, String status) {
        this.user = user;
        this.media = media;
        this.score = score;
        this.status = status;
    }

    public UserGraphql getUser() {
        return user;
    }

    public void setUser(UserGraphql user) {
        this.user = user;
    }

    public MediaGraphQl getMedia() {
        return media;
    }

    public void setMedia(MediaGraphQl media) {
        this.media = media;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EntryGraphQl(JSONObject obj, UserGraphql user) {
        setMedia(new MediaGraphQl((JSONObject) obj.get("media")));
        setScore(Integer.parseInt(obj.get("score").toString()));
        setStatus(obj.get("status").toString());
        setUser(user);
    }

//    public Entry getEntry(@Autowired ModelMapper modelMapper){
//        Entry entry = modelMapper.map(this, Entry.class);
//        return entry;
//    }
}
