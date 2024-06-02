package ca.parimal.connectz.model.entities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
public class MediaListCollection {
    public static final String QUERY = "entries { "+Media.QUERY+"score(format: POINT_100),status}";
    public User user;
    public ArrayList<Entry> entries;

    public MediaListCollection() {}
    public MediaListCollection(JSONArray obj) {
        this.entries = new ArrayList<>();
        System.out.println(obj.toString());
         for (int i=0;i< obj.size();i++) {
             JSONArray  entriesJSONObject = (JSONArray) ((JSONObject)obj.get(i)).get("entries");
             for(int j=0;j<entriesJSONObject.size();j++) {
                 //System.out.println(entriesJSONObject.get(j).toString());
                 Entry entryObj = new Entry((JSONObject) entriesJSONObject.get(j),user);
                 entries.add(entryObj);
             }
         }
    }
    public MediaListCollection(JSONArray obj, User user) {
        this(obj);
        this.user = user;
    }
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
