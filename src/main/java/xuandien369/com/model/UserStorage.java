package xuandien369.com.model;

import java.util.HashSet;
import java.util.Set;

public class UserStorage {

    private static UserStorage instance;
	public static int countMember = 0;
    private Set<String> users;

    private UserStorage() {
        users = new HashSet<>();
    }

    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUser(String userName) throws Exception {
        if (users.contains(userName)) {
            throw new Exception("User aready exists with userName: " + userName);
        }
        users.add(userName);
    }
}
