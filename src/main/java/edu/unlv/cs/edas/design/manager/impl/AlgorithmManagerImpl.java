package edu.unlv.cs.edas.design.manager.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.design.domain.ImmutableAlgorithm;
import edu.unlv.cs.edas.design.domain.MutableAlgorithm;
import edu.unlv.cs.edas.design.manager.AlgorithmChangedEvent;
import edu.unlv.cs.edas.design.manager.AlgorithmManager;
import edu.unlv.cs.edas.design.persistence.AlgorithmRepository;

@Component @Scope("singleton")
public class AlgorithmManagerImpl implements AlgorithmManager {

	@Autowired AlgorithmRepository repository;
	
	@Autowired ApplicationEventPublisher publisher;
	
	@Override
	public ImmutableAlgorithm get(ObjectId id) {
		MutableAlgorithm algorithm = repository.findOne(id);
		if (algorithm == null) {
			return null;
		}
		return new ImmutableAlgorithm(algorithm);
	}

	@Override
	public ImmutableAlgorithm get(String id) {
		return get(new ObjectId(id));
	}

	@Override
	public ImmutableAlgorithm save(MutableAlgorithm algorithm) {
		ImmutableAlgorithm savedAlgorithm = new ImmutableAlgorithm(repository.save(algorithm));
		publisher.publishEvent(new AlgorithmChangedEvent(this, savedAlgorithm.getId()));
		return savedAlgorithm;
	}

	@Override
	public void delete(ObjectId id) {
		repository.delete(id);
	}

	@Override
	public void delete(String id) {
		delete(new ObjectId(id));
	}

	@Override
	public Collection<ImmutableAlgorithm> getAllAlgorithmsByOwner(ObjectId ownerId) {
		Collection<ImmutableAlgorithm> algorithms = new ArrayList<ImmutableAlgorithm>();
		for (MutableAlgorithm algorithm : repository.findByOwner(ownerId)) {
			algorithms.add(new ImmutableAlgorithm(algorithm));
		}
		return algorithms;
	}

	@Override
	public Collection<ImmutableAlgorithm> getAllAlgorithmsByOwner(String ownerId) {
		return getAllAlgorithmsByOwner(new ObjectId(ownerId));
	}

}
