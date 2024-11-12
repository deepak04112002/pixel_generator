# Pixel Generator Documentation
## Overview
The Pixel Generator is a Spring Boot application that creates and tracks invisible pixels for web analytics. It provides endpoints for generating tracking pixels and processing tracking events through Kafka integration.
## Table of Contents
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [API Reference](#api-reference)
- [Configuration](#configuration)
- [Usage Examples](#usage-examples)
## Getting Started
### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Apache Kafka
- Spring Boot 3.x
### Installation
1. Clone the repository:
```bash
git clone https://github.com/yourusername/pixel-generator.git
```
2. Build the project:
```bash
mvn clean install
```
3. Run the application:
```bash
mvn spring-boot:run
```
### API Reference
#### Generate Tracking Pixel
``` bash
GET /generate-pixel
```
Returns an HTML img tag containing an invisible tracking pixel.
Example Response:
```bash
<img src="http://localhost:8080/pixel.gif?id=123e4567-e89b-12d3-a456-426614174000" 
     width="1" 
     height="1" 
     alt="Pixel Image" 
     style="position: absolute; visibility: hidden; left: -9999px;" />
```
#### Track Pixel Hit
```bash
GET /pixel.gif?id={pixelId}
```
##### Parameters:
- id (required): Unique identifier for the tracking pixel
##### Tracks pixel hits and collects:
- Pixel ID
- IP Address
- User Agent
- Timestamp
Returns a 1x1 transparent GIF image.
### Configuration
Application Properties
```bash
# Server Configuration
server.port=8080

# Kafka Configuration
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=pixel-generator-group

# Logging Configuration
logging.level.com.pixelgenerator=INFO
```
#### Kafka Integration
The application uses Kafka for event processing:
- Events are produced via TrackingEventProducer
- Each pixel hit generates a tracking event
- Events contain visitor information and timestamp
### Usage Examples
#### Basic Implementation
1. Generate a tracking pixel:
```bash
curl http://localhost:8080/generate-pixel
```
2. Add the returned HTML to your webpage:
```bash
<!-- Add this to your HTML where tracking is needed -->
<div>
    <!-- Tracking Pixel will be invisible -->
    <img src="http://localhost:8080/pixel.gif?id=your-generated-id" 
         width="1" 
         height="1" 
         alt="Pixel Image" 
         style="position: absolute; visibility: hidden; left: -9999px;" />
</div>
```
### Tracking Events
The system automatically:
- Logs pixel hits
- Records visitor information
- Processes events through Kafka
### Security Considerations
#### Data Collection
- IP addresses are collected
- User agent strings are stored
- Consider GDPR compliance requirements

### Troubleshooting
#### Common Issues
1. Pixel not loading
- Check server connectivity
- Verify pixel ID format
- Check network permissions
2. Kafka connection issues
- Verify Kafka is running
- Check connection strings
- Review broker settings
#### Logging
- Application logs are available in the console
- Kafka events can be monitored through Kafka tools
- Use logging level INFO for tracking events
