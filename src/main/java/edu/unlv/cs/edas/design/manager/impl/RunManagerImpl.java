package edu.unlv.cs.edas.design.manager.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.design.domain.ImmutableRun;
import edu.unlv.cs.edas.design.domain.MutableRun;
import edu.unlv.cs.edas.design.manager.RunManager;
import edu.unlv.cs.edas.design.persistence.RunRepository;

@Component @Scope("singleton")
public class RunManagerImpl implements RunManager {

	@Autowired RunRepository repository;
	
	@Override
	public ImmutableRun get(ObjectId id) {
		return new ImmutableRun(repository.findOne(id));
	}

	@Override
	public ImmutableRun get(String id) {
		return get(new ObjectId(id));
	}

	@Override
	public ImmutableRun save(MutableRun run) {
		return new ImmutableRun(repository.save(run));
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
	public Collection<ImmutableRun> getAllByOwner(ObjectId ownerId) {
		Collection<ImmutableRun> runs = new ArrayList<ImmutableRun>();
		for (MutableRun run : repository.findByOwner(ownerId)) {
			runs.add(new ImmutableRun(run));
		}
		return runs;
	}

	@Override
	public Collection<ImmutableRun> getAllByOwner(String ownerId) {
		return getAllByOwner(new ObjectId(ownerId));
	}

}
