package com.guillem.kafka;

import com.guillem.model.Creator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by guillem on 30/01/2019.
 */
@Component
public class KafkaSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);
    private static final String TOPIC = "creators";


    @Autowired
    private KafkaTemplate<String, Creator> kafkaTemplate;

    public void sendCreator(Creator payload) {
        LOGGER.info("sending payload='{}'", payload);
        kafkaTemplate.send(TOPIC, payload);
    }
}
