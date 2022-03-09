> First Step: Adding maven aws java SDK in the fellow link : https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk

> Create bucket in aws console (search for s3), then configure an enum class for bucket name that enable later to store images in the bucket.

> Create a class for configuring aws, and annotated the class with @Configuration (Configure AWS credentials + AmazonS3Client) :

1. Go to aws console
2. Security credentials
3. Generate access key file
4. Put the access key and secret key in "AWSCredentials"

````java
AWSCredentials awsCredentials = new BasicAWSCredentials(
        "your access key",
        "your secret key"
);
````

5. Specify the region of your bucket "AmazonS3ClientBuilder"

````java
AmazonS3ClientBuilder
    .standard()
    .withRegion("region of your bucket") // specify the region of yr bucket
    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
    .build();
````

> In this case, we won't interact with a real DB, but we will use a fake DB of users (create in memory db) => check DataStore package

> The next step, is about creating the upload image controller (UserController) and the implementation 

> Front-End part : To test APIs => Check "aws-upload-app" in java main package