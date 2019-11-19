package jp.isols.aws.sqs.sqssubscriber.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

import lombok.Getter;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class AwsDemoSqsClient {

    @Value("${cloud.aws.region.static}")
    @Getter
    private String region;

    @Value("${cloud.aws.end-point.url}")
    @Getter
    private String sqsUrl;

    @Value("${cloud.aws.credentials.access-key}")
    @Getter
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    @Getter
    private String secretKey;

    @Getter
    private AmazonSQS basicSqsClient;

    @PostConstruct
    private void init() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        basicSqsClient = AmazonSQSClientBuilder
                            .standard()
                            .withCredentials(new AWSStaticCredentialsProvider(credentials))
                            .withRegion(region)
                            .build();
    }
}
