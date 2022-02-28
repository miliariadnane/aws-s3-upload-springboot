package dev.nano.awsupload.service;

import dev.nano.awsupload.datastore.FakeUserDataStore;
import dev.nano.awsupload.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDataAccessService {

    private final FakeUserDataStore fakeUserDataStore;

    List<User> getUsers() {
        return fakeUserDataStore.getUsers();
    }
}
