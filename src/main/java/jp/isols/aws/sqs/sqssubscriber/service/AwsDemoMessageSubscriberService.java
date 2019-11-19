package jp.isols.aws.sqs.sqssubscriber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import lombok.Getter;

@Component
public class AwsDemoMessageSubscriberService {

    @Autowired
    private AwsDemoSqsClient basicSqsClient;

    @Value("${cloud.aws.end-point.polling-threads}")
    @Getter
    private int pollingThreads;

    @Value("${cloud.aws.end-point.polling-rate}")
    @Getter
    private int pollingRate;

    private ScheduledThreadPoolExecutor executer;

    @PostConstruct
    private void startPolling() {
        this.executer = new ScheduledThreadPoolExecutor(pollingThreads);

        for (int worker = 0; worker < this.pollingThreads; worker++) {
            this.executer.scheduleAtFixedRate(new ReceiveMessageCommand(basicSqsClient), 0, this.pollingRate, TimeUnit.MILLISECONDS);
        }
    }
}