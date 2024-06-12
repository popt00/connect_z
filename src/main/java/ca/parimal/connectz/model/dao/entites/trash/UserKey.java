package ca.parimal.connectz.model.dao.entites.trash;

import java.io.Serializable;
import java.util.Objects;

public class UserKey implements Serializable {
    private Integer userId;
    public UserKey(Integer userId) {}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserKey userKey = (UserKey) o;
        return Objects.equals(userId, userKey.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }
}
