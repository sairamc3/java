# Open Telemetry: Observability with Java

> This folder is dedicated to the course in plurasight about observability in java.

## Three Pillers of Observability

1. Metrics
2. Logs
3. Traces 
4. Alerts (Special)

### Metrics
1. Gauge
2. Counter
3. Histogram

#### Built-in metrics

1. JVM metrics
2. CPU metrics
3. File descriptor metrics
4. Kafka consumer/producer metrics
5. uptime

#### Custom metrics

1. Concurrent GET requests
2. Endpoint success calls
3. HTTP request duration

### Logs

#### Log Levels
- **Error:** A serious issue causing a failure
- **Warning:** A potential problem that needs attention
- **Info:** General details about normal operations
- **Debub:** Detailed logs for troubleshooting
- **Trace:** Most detailed logs for tracing execution

## Open Telemetry

### Observability Providers
* Grafana
* Datadog
* Splunk
* New Relic
* Dynatrace
* Tanzu Observability

> Open telemetry is a collection of APIs, SDKs, and tools. Use it to instrument, generate, collect and export telemetry data (metrics, logs, and traces) to help you analyze your software's performance and behaviour. 

### Architecture

Applications -> Collector (OTEL collector) -> Observability Platform(s)

## Demo

For running the application we need Redis database. Here is the docker command to enable it. 

```bash
docker run -d --rm --name redis -p 6379:6379 redis:latest
```
The above command is required only during the initial setup. Later it has been moved to docker-compose file. 

Although there are many things done during the setup, the easiest way to run them with the final product would be

#### Orders Service
```bash
mvn clean package
docker build -t orders-service:1.0 .
```

#### Delivery Service
```bash
mvn clean package
docker build -t delivery-service:1.0 .
```

The above are the application docker image. Rest of the configurations are already present in the docker compose files. 

If there is any issue with setting up the application, then please check the paths mentioned in the configuration files and spring applications. 


