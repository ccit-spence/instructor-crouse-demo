package com.demo.integration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;

@Component
@ConfigurationProperties("integration.courses")
public class CourseIntegration {

    static Logger logger = LoggerFactory.getLogger(CourseIntegration.class);

    private LoadBalancerClient loadBalancer;

    @Autowired
    public CourseIntegration(LoadBalancerClient loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    @Getter
    @Setter
    private String uri = "http://localhost:8075/courses";

    @HystrixCommand(fallbackMethod = "defaultLink")
    public Link findByInstructors(Map<String, Object> parameters, HttpHeaders headers) {

        URI coursesUri = URI.create(uri);

        try {
            ServiceInstance instance = loadBalancer.choose("course-service");
            coursesUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
        }
        catch (RuntimeException e) {
            // Eureka not available
        }

        logger.info("Trying to access the course service at {}…", coursesUri);

        Traverson traverson = new Traverson(coursesUri, MediaTypes.HAL_JSON);
        Link link = traverson.follow("courses", "search", "by-related")
                .withHeaders(headers)
                .withTemplateParameters(parameters).asLink();

        logger.info("Found courses link pointing to {}.", link.getHref());

        return link;

    }

    public Link defaultLink(Map<String, Object> parameters, HttpHeaders headers) {
        return null;
    }
}
