package edu.unlv.cs.edas.user.domain;

import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;

public interface User extends UserDetails {

	ObjectId getId();
	
	String getName();
	
	String getPassword();
	
}
