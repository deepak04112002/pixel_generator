package com.pixelgenerator.pixel.services;

import com.pixelgenerator.pixel.model.TrackingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TrackingEventProducer {

    public static final String TOPIC = "tracking-events";

    @Autowired
    private KafkaTemplate<String, TrackingEvent> kafkaTemplate;

    public void sendTrackingEvent(TrackingEvent event) {
        kafkaTemplate.send(TOPIC, event.getPixelId(),event);
    }
}
