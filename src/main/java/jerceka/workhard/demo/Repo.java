package jerceka.workhard.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Bank",path="bank")
public interface Repo extends JpaRepository<Bank, Integer>{
	
}
