package com.pixelgenerator.pixel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tracking_events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pixel_id", nullable = false,length = 50)
    private String pixelId;

    @Column(name="timestamp")
    private LocalDateTime timestamp;

    @Column(name = "ip_address", nullable = false, length = 50)
    private String ipAddress;


    @Column(name = "user_agent", nullable = false, length = 255)
    private String userAgent;

}
