Built in Observability
======================

rcalc-trail-svc comes with some observability features out of the. It relies on [Springboot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html), for most of those features. Below is a list of pre-enabled observability features for rcalc-trail-svc.

## Metrics and Datadog

rcalc-trail-svc uses the [Actuator StatsD Integration](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-metrics-export-statsd) to publish metrices to datadog.

It is enabled in [application.yaml](https://github.com/ktech-org/rcalc-trail-svc/blob/master/springboot-adapter/src/main/resources/application.yml#L11).

```yaml
management:
  metrics:
    export:
      ## ATTENTION: These are all default values,
      ## There are here as reference only
      statsd:
        enabled: true
        step: 1m
        flavor: datadog
        host: localhost
        post: 8125

```

Metrics are also available under actuator metrics endpoints:

* list of all metrics: `http://localhost:8080/actuator/metrics`
* individual metrics: `http://localhost:8080/actuator/metrics/{requiredMetricName}`

## Health a.k.a. healthchecks

[Actuator Health](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-health) endpoint is enabled, which is available at `http://localhost:8080/actuator/health`

By default, actuator comes with a few [pre-built health indicators](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#auto-configured-healthindicators), which are aggregated in the health endpoint. If you want to add/plugin your own 
health indicators, say to incorporate the health of an upstream service, then follow the instructions on writing [custom health indicators](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#writing-custom-healthindicators). 

## Git commit info

Git commit information is available at : `http://localhost:8080/actuator/info`


