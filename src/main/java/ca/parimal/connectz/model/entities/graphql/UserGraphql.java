package ca.parimal.connectz.model.entities.graphql;

import ca.parimal.connectz.model.entities.dao.User;
import org.json.simple.JSONObject;

public class UserGraphql extends User {
    public static final String QUERY = "user {name,id}";
    public UserGraphql() {
        super();
    }
    public UserGraphql(JSONObject user){
        setName(user.get("name").toString());
        setAnilistUserId(Integer.parseInt(user.get("id").toString()));
    }

}
