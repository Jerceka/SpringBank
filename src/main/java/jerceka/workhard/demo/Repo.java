package jerceka.workhard.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Bank",path="bank")
public interface Repo extends JpaRepository<Bank, Integer>{
	List<Bank> findByName(String name);
	List<Bank> findByPassword(String password);
	Boolean existsByName(String name);
	Boolean existsBypassword(String password);
	List<Bank> deleteByName(String name);
}
