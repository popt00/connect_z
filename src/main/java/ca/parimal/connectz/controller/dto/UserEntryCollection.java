package ca.parimal.connectz.controller.dto;
import ca.parimal.connectz.controller.dto.entities.EntryGraphQl;
import ca.parimal.connectz.controller.dto.entities.MediaGraphQl;
import ca.parimal.connectz.controller.dto.entities.UserGraphql;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserEntryCollection {
    //this is for entr collection
    public static final String QUERY = "entries { "+ MediaGraphQl.QUERY+"score(format: POINT_100),status}";
    public UserGraphql user;
    public ArrayList<EntryGraphQl> entries;

    public UserEntryCollection() {}
    public UserEntryCollection(JSONArray obj) {
        this.entries = new ArrayList<>();
        System.out.println(obj.toString());
        //ensurng unique mediaIds
        Set<Integer> mediaIds = new HashSet<>();
         for (int i=0;i< obj.size();i++) {
             JSONArray  entriesJSONObject = (JSONArray) ((JSONObject)obj.get(i)).get("entries");
             for(int j=0;j<entriesJSONObject.size();j++) {
                 //System.out.println(entriesJSONObject.get(j).toString());
                 EntryGraphQl entryObj = new EntryGraphQl((JSONObject) entriesJSONObject.get(j),user);
                 Integer mediaId=entryObj.getMedia().getAnilistMediaId();
                 if(!mediaIds.contains(mediaId)) {
                     mediaIds.add(mediaId);
                     entries.add(entryObj);
                 }
             }
         }
    }
    public UserEntryCollection(JSONArray obj, UserGraphql user) {
        this(obj);
        this.user = user;
    }
    public ArrayList<EntryGraphQl> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<EntryGraphQl> entries) {
        this.entries = entries;
    }

    public UserGraphql getUser() {
        return user;
    }

    public void setUser(UserGraphql user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MediaListCollection{" +
                "user=" + user.toString() +
                ", entries=" + entries.toString() +
                '}';
    }


}
