package dev.nano.awsupload.bucket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BucketName {

    USER_IMAGE("aws-upload-image-nanodev");

    private final String bucketName;
}
