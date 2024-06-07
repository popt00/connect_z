package ca.parimal.connectz.model.graphql;
import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.model.graphql.entities.EntryGraphQl;
import ca.parimal.connectz.model.graphql.entities.MediaGraphQl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
public class UserEntryCollection {
    public static final String QUERY = "entries { "+ MediaGraphQl.QUERY+"score(format: POINT_100),status}";
    public User user;
    public ArrayList<Entry> entries;

    public UserEntryCollection() {}
    public UserEntryCollection(JSONArray obj) {
        this.entries = new ArrayList<>();
        System.out.println(obj.toString());
         for (int i=0;i< obj.size();i++) {
             JSONArray  entriesJSONObject = (JSONArray) ((JSONObject)obj.get(i)).get("entries");
             for(int j=0;j<entriesJSONObject.size();j++) {
                 //System.out.println(entriesJSONObject.get(j).toString());
                 Entry entryObj = new EntryGraphQl((JSONObject) entriesJSONObject.get(j),user);
                 entries.add(entryObj);
             }
         }
    }
    public UserEntryCollection(JSONArray obj, User user) {
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
