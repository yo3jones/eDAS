package edu.unlv.cs.edas.design.persistence;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.unlv.cs.edas.design.domain.DesignGraphDetails;

public interface DesignGraphRepository extends CrudRepository<DesignGraphDetails, ObjectId>{

	@Query(value="{ 'owner' : ?0 }", fields="{ 'name' : 1 }")
	Collection<DesignGraphDetails> findDesignGraphDetailsForUser(ObjectId userId);
	
}
