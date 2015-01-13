package com.demo.repository;

import com.demo.domain.Related;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "related", path = "related")
public interface RelatedRepository extends JpaRepository<Related, Long> {

}
