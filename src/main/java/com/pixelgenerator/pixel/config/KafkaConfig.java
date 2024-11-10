package com.pixelgenerator.pixel.config;

import com.pixelgenerator.pixel.model.TrackingEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic trackingTopic() {
        return TopicBuilder.name("tracking-events")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
