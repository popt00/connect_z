package ca.parimal.connectz.controller.dto;

import ca.parimal.connectz.controller.dto.entities.UserGraphql;
import ca.parimal.connectz.model.dao.entites.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UserEntryCollectionFactory {
    public UserEntryCollection build(String userName){
        JSONObject mediaListObject = getMediaObject(userName);
        if(mediaListObject == null){
            return null;
        }
        UserGraphql user = new UserGraphql((JSONObject) mediaListObject.get("user"));
        UserEntryCollection userEntryCollection = new UserEntryCollection((JSONArray) mediaListObject.get("lists"),user);
        return userEntryCollection;
    }
    public JSONObject getMediaObject(String userName){
        JSONObject obj = new GraphqlRequest().send(userName,getQuery(userName));
        if(obj == null) return null;
        JSONObject mediaListObject = (JSONObject) ((JSONObject) obj.get("data")).get("MediaListCollection");;
        return mediaListObject;
    }
    public String getQuery(String userName){
        return "query {MediaListCollection(userName: \""+userName+"\", type: ANIME){ "+ UserGraphql.QUERY+", lists{ "+ UserEntryCollection.QUERY +"}}}";
    }
}
