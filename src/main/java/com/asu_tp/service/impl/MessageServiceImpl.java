package com.asu_tp.service.impl;


import com.asu_tp.repo.DirectionalRepository;
import com.asu_tp.service.MessageService;
import com.asu_tp.service.WitsDataParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private DirectionalRepository directionalRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    WitsDataParser parser = new WitsDataParser();

    int count=0;
    ArrayList<String> receiveDataList = new ArrayList<>();

    @Override
    public byte[] processMessage(byte[] message) {
        String messageContent = new String(message);
      //  LOGGER.info("Receive message: {}", messageContent);
        System.out.println("Receive message: "+ messageContent);

        parser.parse(messageContent);
        parser.writeToDB(directionalRepository);
      //  String responseContent = String.format("Message \"%s\" is processed", messageContent);
        String responseContent = "";

        return responseContent.getBytes();
    }





}
