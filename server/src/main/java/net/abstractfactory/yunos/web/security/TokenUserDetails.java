package net.abstractfactory.yunos.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.abstractfactory.yunos.domain.User;
import net.abstractfactory.yunos.domain.auth.Token;

/**
 * This object wraps {@link User} and makes it {@link UserDetails} so that
 * Spring Security can use it.
 */
public class TokenUserDetails implements UserDetails {

	private Token token;

	public TokenUserDetails(Token token) {
		this.token = token;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		/*
		 * for (String role : user.getRoles()) { authorities.add(new
		 * SimpleGrantedAuthority(role)); }
		 */

		authorities.add(new SimpleGrantedAuthority("ROLE_APPLICATION"));

		return authorities;
	}

	@Override
	public String getPassword() {
		return token.getPasswordHash();
	}

	@Override
	public String getUsername() {
		return token.getId();
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
		return this == o || o != null && o instanceof TokenUserDetails
				&& Objects.equals(token, ((TokenUserDetails) o).token);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(token);
	}

	@Override
	public String toString() {
		return "UserContext{" + "user=" + token + '}';
	}
}
