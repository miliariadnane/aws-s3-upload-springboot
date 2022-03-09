package dev.nano.awsupload.service;

import dev.nano.awsupload.bucket.BucketName;
import dev.nano.awsupload.domain.User;
import dev.nano.awsupload.fileStore.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDataAccessService userDataAccessService;
    private final FileStore fileStore;

    public List<User> getUsers() {
        return userDataAccessService.getUsers();
    }

    public void uploadUserImage(UUID userId, MultipartFile file) {

        // 1. Check if image is not empty
        isFileEmpty(file);

        // 2. If file is an image
        isImage(file);

        // 3. The user exists in our DB
        User user = getUserOrThrow(userId);

        // 4. Grab some metadata from file if any
        Map<String, String> metadata = extractMetadata(file);

        // 5. Store the image in s3 and update database (userImage) with s3 image link
        String path = String.format("%s/%s", BucketName.USER_IMAGE.getBucketName(), user.getUserId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            user.setUserImage(Optional.ofNullable(filename));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private User getUserOrThrow(UUID userId) {
        return userDataAccessService
                .getUsers()
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User %s not found", userId)));
    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
    }

    public byte[] downloadUserImage(UUID userId) {
        User user = getUserOrThrow(userId);

        String path = String.format("%s/%s",
                BucketName.USER_IMAGE.getBucketName(),
                user.getUserId());

        return user.getUserImage()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }
}
