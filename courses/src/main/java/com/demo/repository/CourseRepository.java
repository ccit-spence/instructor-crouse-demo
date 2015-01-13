package com.demo.repository;

import com.demo.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @RestResource(rel = "by-related")
    @Query("select c from Course c inner join c.related r where r.relType = :relType and r.relatedEntityId = :relatedId")
    Page<Course> findByRelated(
            @Param("relType") String relLink,
            @Param("relatedId") String relatedId,
            Pageable pageable
    );

}
