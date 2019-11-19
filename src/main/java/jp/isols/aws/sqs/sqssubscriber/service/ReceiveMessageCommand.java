package jp.isols.aws.sqs.sqssubscriber.service;

import com.amazonaws.services.sqs.model.ReceiveMessageResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceiveMessageCommand implements Runnable {

    private AwsDemoSqsClient basicSqsClient;

    public ReceiveMessageCommand(AwsDemoSqsClient basicSqsClient) {
        this.basicSqsClient = basicSqsClient;
    }

    @Override
    public void run() {
        ReceiveMessageResult result = basicSqsClient
                                        .getBasicSqsClient()
                                        .receiveMessage(basicSqsClient.getSqsUrl());
        log.info("Message Received {}", result);
    }
}