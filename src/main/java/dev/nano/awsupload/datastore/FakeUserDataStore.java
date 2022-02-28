package dev.nano.awsupload.datastore;

import dev.nano.awsupload.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserDataStore {

    private static final List<User> USERS = new ArrayList<>();

    static {
        USERS.add(
                new User(UUID.randomUUID(),
                        "miliariadnane",
                        "miliari",
                        "adnane",
                        null
                )
        );

        USERS.add(
                new User(UUID.randomUUID(),
                        "jogndoe",
                        "doe",
                        "john",
                        null
                )
        );
    }

    public List<User> getUsers() {
        return USERS;
    }
}
