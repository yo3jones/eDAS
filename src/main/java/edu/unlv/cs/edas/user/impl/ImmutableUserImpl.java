package edu.unlv.cs.edas.user.impl;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;

import edu.unlv.cs.edas.user.domain.MutableUser;
import edu.unlv.cs.edas.user.domain.User;

public final class ImmutableUserImpl implements User {

	private static final long serialVersionUID = 1311360925229671584L;
	
	final private User mutableUser;
	
	ImmutableUserImpl(User user) {
		mutableUser = new MutableUser(user);
	}
	
	@Override
	public ObjectId getId() {
		return mutableUser.getId();
	}
	
	@Override
	public final String getName() {
		return mutableUser.getName();
	}
	
	@Override
	public String getPassword() {
		return mutableUser.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return mutableUser.getAuthorities();
	}

	@Override
	public String getUsername() {
		return mutableUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return mutableUser.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return mutableUser.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return mutableUser.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return mutableUser.isEnabled();
	}
}
