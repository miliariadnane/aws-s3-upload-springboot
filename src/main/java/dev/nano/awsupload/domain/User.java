package dev.nano.awsupload.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class User {

    private UUID userId;
    private String username;
    private String lastName;
    private String firstName;
    private Optional<String> userImage; // S3 KEY
}
