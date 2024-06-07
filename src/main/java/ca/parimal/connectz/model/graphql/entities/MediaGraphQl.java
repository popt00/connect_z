package ca.parimal.connectz.model.graphql.entities;

import ca.parimal.connectz.model.dao.entites.Media;
import org.json.simple.JSONObject;

public class MediaGraphQl extends Media {
    public static final String QUERY="media{id, title {romaji}}";
    public MediaGraphQl (JSONObject obj){
        setAnilistMediaId(Integer.parseInt(obj.get("id").toString()));
        setTitle(((JSONObject)obj.get("title")).get("romaji").toString());
    }
}
