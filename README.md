> First Step: Adding maven aws java SDK in the fellow link : https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk

> Configure AWS credentials (should that you have an account in aws platform) :
1. Go to aws console
2. Security credentials
3. Generate an access key
4. Download the file with the access key and secret key

> Create a class for configuring aws, and annotated the class with @Configuration

> Create bucket in aws console (search for s3), then configure an enum class for bucket name that enable later to store images in the bucket.

> In our case, we won't interact with a real DB, but we will use a fake DB (create in memory db) => check DataStore package

> The next step, is about creating the upload image endpoint