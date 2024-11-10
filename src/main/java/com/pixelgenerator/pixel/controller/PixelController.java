package com.pixelgenerator.pixel.controller;

import com.pixelgenerator.pixel.model.TrackingEvent;
import com.pixelgenerator.pixel.services.TrackingEventProducer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@Slf4j
public class PixelController {

    @Autowired
    private TrackingEventProducer trackingEventProducer;

    @GetMapping("/generate-pixel")
    public String generatePixel() {
        String pixelId = UUID.randomUUID().toString();
        String pixelUrl = "http://localhost:8080/pixel.gif?id=" + pixelId;
        return "<img src=\"" + pixelUrl + "\" width=\"1\" height=\"1\" alt=\"Pixel Image\" style=\"position: absolute; visibility: hidden; left: -9999px;\" />";
    }

    @GetMapping(value = "/pixel.gif",produces = MediaType.IMAGE_GIF_VALUE)
    public byte[] trackPixel(@RequestParam String id, HttpServletRequest request) {
        TrackingEvent event = new TrackingEvent();
        event.setPixelId(id);
        event.setIpAddress(request.getRemoteAddr());
        event.setUserAgent(request.getHeader("User-Agent"));
        event.setTimestamp(LocalDateTime.now());



        log.info("Tracking pixel hit with ID: {}", id); // Add logging
        trackingEventProducer.sendTrackingEvent(event);

        return new byte[]{0x47,0x49,0x46,0x38,0x39,0x61,0x01,0x00,0x01,0x00,(byte)0x80,0x00,0x00,(byte)0xff,(byte)0xff,(byte)0xff,0x00,0x00,0x00,0x2c,0x00,0x00,0x00,0x00,0x01,0x00,0x01,0x00,0x00,0x02,0x02,0x44,0x01,0x00,0x3b};
    }
}
