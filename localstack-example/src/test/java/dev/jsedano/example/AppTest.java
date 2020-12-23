package dev.jsedano.example;

import cloud.localstack.awssdkv1.TestUtils;
import cloud.localstack.docker.LocalstackDockerExtension;
import cloud.localstack.docker.annotation.LocalstackDockerProperties;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(LocalstackDockerExtension.class)
@LocalstackDockerProperties(services = {"s3"}, hostNameResolver = HostNameResolverImpl.class)
public class AppTest {

  @Test
  public void creatingBucketAndPuttingObject() throws IOException {
    String bucketName = "abucket";
    String fileName = "test.txt";
    String fileContent = "a String";

    AmazonS3 s3 = TestUtils.getClientS3();
    s3.createBucket(bucketName); //creating bucket

    InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

    //uploading file to bucket
    s3.putObject(bucketName,fileName, inputStream, new ObjectMetadata());

    //downloading file from bucket
    S3Object s3Object = s3.getObject(bucketName, fileName);
    S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();

    //checking is the same as the info we uploaded
    assertEquals(fileContent, IOUtils.toString(s3ObjectInputStream));
  }

}
