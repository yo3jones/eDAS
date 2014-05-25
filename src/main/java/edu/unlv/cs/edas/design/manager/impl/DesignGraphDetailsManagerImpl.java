package edu.unlv.cs.edas.design.manager.impl;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.design.domain.DesignGraphDetails;
import edu.unlv.cs.edas.design.domain.DesignHashGraph;
import edu.unlv.cs.edas.design.manager.DesignGraphDetailsManager;
import edu.unlv.cs.edas.design.persistence.DesignGraphRepository;

/**
 * An implementation of {@link AbstractGraphManager} for design graphs.
 * 
 * @author Chris Jones
 *
 */
@Component @Scope(SCOPE_SINGLETON)
public class DesignGraphDetailsManagerImpl implements DesignGraphDetailsManager {
	
	@Autowired DesignGraphRepository repository;
	
	@Override
	public DesignGraphDetails get(String id) {
		return get(new ObjectId(id));
	}
	
	@Override
	public DesignGraphDetails get(ObjectId id) {
		DesignGraphDetails graphDetails = repository.findOne(id);
		if (graphDetails == null) {
			return null;
		}
		graphDetails.setGraph(new DesignHashGraph(graphDetails.getGraph()));
		return graphDetails;
	}
	
	@Override
	public ObjectId save(DesignGraphDetails graphDetails) {
		if (graphDetails.getId() == null) {
			graphDetails.setId(new ObjectId());
		}
		graphDetails = repository.save(graphDetails);
		return graphDetails.getId();
	}
	
	@Override
	public void delete(String id) {
		delete(new ObjectId(id));
	}
	
	@Override
	public void delete(ObjectId id) {
		repository.delete(id);
	}
	
	@Override
	public Collection<DesignGraphDetails> findAllOwnedBy(ObjectId ownerId) {
		return repository.findDesignGraphDetailsForUser(ownerId);
	}
	
}
