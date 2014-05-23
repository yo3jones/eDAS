package edu.unlv.cs.edas.user.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.user.domain.MutableUser;
import edu.unlv.cs.edas.user.domain.User;
import edu.unlv.cs.edas.user.domain.UserAlreadyExistsException;
import edu.unlv.cs.edas.user.domain.UserManager;
import edu.unlv.cs.edas.user.persistence.UserRepository;

@Component @Scope("singleton")
public class UserManagerImpl implements UserManager, UserDetailsService {

	@Autowired UserRepository repository;
	@Autowired MongoTemplate template;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = repository.findByName(username);
		if (userDetails == null) {
			throw new UsernameNotFoundException("Could not find user. [username=" + username + "]");
		}
		return userDetails;
	}
	
	@Override
	public User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@Override
	public User get(String id) {
		return new ImmutableUserImpl(getMutable(id));
	}

	@Override
	public MutableUser getMutable(String id) {
		return repository.findOne(new ObjectId(id));
	}

	@Override
	public MutableUser create(String name, String password) throws UserAlreadyExistsException {
		MutableUser existingUser = repository.findByName(name);
		
		if (existingUser != null) {
			throw new UserAlreadyExistsException();
		}
		
		MutableUser newUser = new MutableUser();
		newUser.setName(name);
		newUser.setPassword(password);
		return save(newUser);
	}

	@Override
	public MutableUser save(MutableUser user) throws UserAlreadyExistsException {
		return repository.save(user);
	}
	
}
