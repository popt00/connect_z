package ca.parimal.connectz.controller;

import ca.parimal.connectz.model.entities.MediaListCollection;
import ca.parimal.connectz.model.entities.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MediaListController {
    public MediaListCollection getMediaList(String userName){

        String query="query {MediaListCollection(userName: \""+userName+"\", type: ANIME){ "+ User.QUERY+", lists{ "+ MediaListCollection.QUERY +"}}}";
        JSONObject obj = new GraphqlFetch().fetch(userName,query);
        //System.out.println(obj.toString());
        JSONObject data = (JSONObject) obj.get("data");
        JSONObject mediaListObject = (JSONObject) data.get("MediaListCollection");;

        JSONObject userJSONObject = (JSONObject) mediaListObject.get("user");
        //System.out.println(userJSONObject);
        User user = new User(userJSONObject);
        JSONArray mediaArray = (JSONArray) mediaListObject.get("lists");

        MediaListCollection mediaListCollection = new MediaListCollection(mediaArray,user);
        System.out.println("output: "+mediaListCollection);
        return mediaListCollection;
    }
}
