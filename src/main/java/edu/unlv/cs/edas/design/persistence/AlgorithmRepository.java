package edu.unlv.cs.edas.design.persistence;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.unlv.cs.edas.design.domain.MutableAlgorithm;

public interface AlgorithmRepository extends CrudRepository<MutableAlgorithm, ObjectId> {

	@Query("{ ownerId: ?0 }")
	Collection<MutableAlgorithm> findByOwner(ObjectId ownerId);
	
}
