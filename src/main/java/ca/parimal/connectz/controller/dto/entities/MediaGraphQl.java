package ca.parimal.connectz.controller.dto.entities;

import ca.parimal.connectz.model.dao.entites.Media;
import jakarta.persistence.Column;
import org.json.simple.JSONObject;

public class MediaGraphQl {
    public static final String QUERY="media{id, title {romaji,english}}";
    public MediaGraphQl (JSONObject obj){
        setAnilistMediaId(Integer.parseInt(obj.get("id").toString()));
        JSONObject title =(JSONObject)obj.get("title");
        if(title.get("english")!=null){
            String engTitle = title.get("english").toString();
            if(engTitle.length()>64){engTitle=engTitle.substring(0,64);}
            setTitle(engTitle);
        }
        else{
            String romajiTitle = title.get("romaji").toString();
            if(romajiTitle.length()>64){romajiTitle=romajiTitle.substring(0,64);}
            setTitle(romajiTitle);
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
