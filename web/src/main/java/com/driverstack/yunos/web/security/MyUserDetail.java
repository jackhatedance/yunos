package com.driverstack.yunos.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.driverstack.yunos.domain.User;

/** This object wraps {@link User} and makes it {@link UserDetails} so that Spring Security can use it. */
public class MyUserDetail implements UserDetails {

	private User user;

	public MyUserDetail(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		/*for (String role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role));
		}*/
		
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_DEVELOPER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_APPLICATION"));
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		return this == o
			|| o != null && o instanceof MyUserDetail
			&& Objects.equals(user, ((MyUserDetail) o).user);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(user);
	}

	@Override
	public String toString() {
		return "UserContext{" +
			"user=" + user +
			'}';
	}
}
