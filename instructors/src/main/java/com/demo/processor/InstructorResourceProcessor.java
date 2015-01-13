package com.demo.processor;

import com.demo.domain.Instructor;
import com.demo.integration.CourseIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InstructorResourceProcessor implements ResourceProcessor<Resource<Instructor>> {

    private static final String X_FORWARDED_HOST = "X-Forwarded-Host";
    private final CourseIntegration courseIntegration;
    private final Provider<HttpServletRequest> requestProvider;

    @Override
    public Resource<Instructor> process(Resource<Instructor> resource) {

        Instructor instructor = resource.getContent();

        HttpHeaders headers = new HttpHeaders();

        String header = requestProvider.get().getHeader(X_FORWARDED_HOST);
        if (header != null) {
            headers.put(X_FORWARDED_HOST, Collections.singletonList(header));
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("relType", "instructors");
        parameters.put("relatedId", instructor.getId());

        Link linkDefault = new Link("http://"+headers+".com");

        Link link = courseIntegration.findByInstructors(parameters, headers);
        if (link != null) {
            resource.add(link.withRel("courses"));
        } else {
            resource.add(linkDefault.withRel("did-not-work"));
        }

        return resource;

    }

}
