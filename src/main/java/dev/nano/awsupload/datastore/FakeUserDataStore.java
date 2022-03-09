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
                new User(UUID.fromString("f17e2b01-667d-4901-a605-37ec5d0befad"),
                        "miliariadnane",
                        "miliari",
                        "adnane",
                        null
                )
        );

        USERS.add(
                new User(UUID.fromString("cf46c3ab-5a89-4d6a-8501-40688a667bef"),
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
