package ca.parimal.connectz.model.entities;

import org.json.simple.JSONObject;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class Media {
    public static final String QUERY="media{id, title {romaji}}";
    public int anilistId;
    public String title;
    public Media(){}
    public Media (JSONObject obj){
        anilistId = Integer.parseInt(obj.get("id").toString());
        title = ((JSONObject)obj.get("title")).get("romaji").toString();
    }

    @Override
    public String toString() {
        return "Media{" +
                "anilistId=" + anilistId +
                ", title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnilistId() {
        return anilistId;
    }

    public void setAnilistId(int anilistId) {
        this.anilistId = anilistId;
    }
}
