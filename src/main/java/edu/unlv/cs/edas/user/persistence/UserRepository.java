package edu.unlv.cs.edas.user.persistence;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.unlv.cs.edas.user.domain.MutableUser;

public interface UserRepository extends CrudRepository<MutableUser, ObjectId> {

	@Query(value="{ 'name' : ?0 }")
	MutableUser findByName(String name);
	
}
