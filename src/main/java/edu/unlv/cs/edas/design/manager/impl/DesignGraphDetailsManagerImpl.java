package edu.unlv.cs.edas.design.manager.impl;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.design.domain.ImmutableDesignGraphDetails;
import edu.unlv.cs.edas.design.domain.MutableDesignGraphDetails;
import edu.unlv.cs.edas.design.manager.DesignGraphDetailsChangedEvent;
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
	
	@Autowired ApplicationEventPublisher publisher;
	
	@Override
	public ImmutableDesignGraphDetails get(String id) {
		return get(new ObjectId(id));
	}
	
	@Override
	public ImmutableDesignGraphDetails get(ObjectId id) {
		MutableDesignGraphDetails graphDetails = repository.findOne(id);
		if (graphDetails == null) {
			return null;
		}
		return new ImmutableDesignGraphDetails(graphDetails);
	}
	
	@Override
	public ImmutableDesignGraphDetails save(MutableDesignGraphDetails graphDetails) {
		MutableDesignGraphDetails savedGraphDetails = repository.save(graphDetails);
		
		publisher.publishEvent(new DesignGraphDetailsChangedEvent(this, savedGraphDetails.getId()));
		
		return new ImmutableDesignGraphDetails(savedGraphDetails);
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
	public Collection<ImmutableDesignGraphDetails> findAllOwnedBy(ObjectId ownerId) {
		Collection<ImmutableDesignGraphDetails> graphDetailsToReturn = 
				new ArrayList<ImmutableDesignGraphDetails>();
		for (MutableDesignGraphDetails graphDetails : repository.findByOwner(ownerId)) {
			graphDetailsToReturn.add(new ImmutableDesignGraphDetails(graphDetails));
		}
		return graphDetailsToReturn;
	}
	
}
