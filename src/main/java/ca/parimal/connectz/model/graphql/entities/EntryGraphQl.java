package ca.parimal.connectz.model.graphql.entities;

import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.User;
import org.json.simple.JSONObject;

public class EntryGraphQl extends Entry {
    public EntryGraphQl(JSONObject obj, User user) {
        setMedia(new MediaGraphQl((JSONObject) obj.get("media")));
        setScore(Integer.parseInt(obj.get("score").toString()));
        setStatus(obj.get("status").toString());
        setUser(user);
    }
}
