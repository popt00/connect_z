package ca.parimal.connectz.controller.dto.entities;

import ca.parimal.connectz.model.dao.entites.Media;
import jakarta.persistence.Column;
import org.json.simple.JSONObject;

public class MediaGraphQl {
    private int anilistMediaId;
    private String title;

    public MediaGraphQl(int anilistMediaId, String title) {
        this.anilistMediaId = anilistMediaId;
        this.title = title;
    }

    public int getAnilistMediaId() {
        return anilistMediaId;
    }

    public void setAnilistMediaId(int anilistMediaId) {
        this.anilistMediaId = anilistMediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static final String QUERY="media{id, title {romaji}}";
    public MediaGraphQl (JSONObject obj){
        setAnilistMediaId(Integer.parseInt(obj.get("id").toString()));
        setTitle(((JSONObject)obj.get("title")).get("romaji").toString());
    }
}
