package edu.unlv.cs.edas.design.persistence;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.unlv.cs.edas.design.domain.MutableRun;

public interface RunRepository extends CrudRepository<MutableRun, ObjectId> {

	@Query("{ ownerId : ?0 }")
	Collection<MutableRun> findByOwner(ObjectId ownerId);
	
}
