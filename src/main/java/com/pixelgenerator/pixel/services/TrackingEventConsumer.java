package com.pixelgenerator.pixel.services;

import com.pixelgenerator.pixel.model.TrackingEvent;
import com.pixelgenerator.pixel.repository.TrackingEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TrackingEventConsumer {

    @Autowired
    private TrackingEventRepository repository;

    @KafkaListener(topics = "tracking-events", groupId = "pixel-tracker-group")
    public void consume(TrackingEvent event){repository.save(event); }
}
