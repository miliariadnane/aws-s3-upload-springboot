package dev.nano.awsupload.service;

import dev.nano.awsupload.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDataAccessService userDataAccessService;

    public List<User> getUsers() {
        return userDataAccessService.getUsers();
    }

    public void uploadUserImage(UUID userId, MultipartFile file) {

        // 1. Check if image is not empty
        // 2. If file is an image
        // 3. The user exists in our DB
        // 4. Grab some metadata from file if any
        // 5. Store the image in s3 and update database (userImage) with s3 image link
    }
}
