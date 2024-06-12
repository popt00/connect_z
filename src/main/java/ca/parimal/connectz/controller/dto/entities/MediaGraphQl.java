package ca.parimal.connectz.controller.dto.entities;
import org.json.simple.JSONObject;

import java.nio.charset.StandardCharsets;

public class MediaGraphQl {
    public static final String QUERY="media{id, title {romaji,english}}";
    public MediaGraphQl (JSONObject obj){
        setAnilistMediaId(Integer.parseInt(obj.get("id").toString()));
        JSONObject title =(JSONObject)obj.get("title");
        if(title.get("english")!=null){
            setTitle(title.get("english").toString());
        }
        else{
            setTitle(title.get("romaji").toString());
        }
    }

    private Integer anilistMediaId;
    private String title;

    public MediaGraphQl(Integer anilistMediaId, String title) {
        this.anilistMediaId = anilistMediaId;
        this.title = title;
    }

    public Integer getAnilistMediaId() {
        return anilistMediaId;
    }

    public void setAnilistMediaId(Integer anilistMediaId) {
        this.anilistMediaId = anilistMediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
