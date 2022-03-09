package dev.nano.awsupload.contoller;

import dev.nano.awsupload.domain.User;
import dev.nano.awsupload.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(
            path = "{userId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserImage(@PathVariable("userId")UUID userId,
                                @RequestParam("file")MultipartFile file) {

        userService.uploadUserImage(userId, file);

    }

    @GetMapping("{userId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userId") UUID userId) {
        return userService.downloadUserImage(userId);
    }
}
