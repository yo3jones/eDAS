package edu.unlv.cs.edas.user.domain;


public interface UserManager {

	User getCurrentUser();
	
	User get(String id);
	
	MutableUser getMutable(String id);
	
	MutableUser create(String name, String password) throws UserAlreadyExistsException;
	
	MutableUser save(MutableUser user) throws UserAlreadyExistsException;
	
}
