package za.co.ashtech.trek.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import za.co.ashtech.trek.db.entity.UserRoleEntity;
import za.co.ashtech.trek.db.entity.UserEntity;
import za.co.ashtech.trek.db.repository.UserDBRepository;

@Service
public class TrekUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDBRepository dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = null;
		try {
			user = dao.findByUsername(username);
		} catch (EmptyResultDataAccessException e) {
			 throw new UsernameNotFoundException(username);
		 }catch (Exception e) {
			 throw new UsernameNotFoundException(username);
		}
		return new MyUserPrincipal(user);
	}
	
	/* inner class for user details */
	class MyUserPrincipal implements UserDetails {

		private static final long serialVersionUID = 1L;
		private UserEntity user;

	    public MyUserPrincipal(UserEntity user) {
	        this.user = user;
	    }

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
			List<SimpleGrantedAuthority> roles = new ArrayList<>();
			for(UserRoleEntity ure:this.user.getTrekRoles()) {
				roles.add(new SimpleGrantedAuthority(ure.getAuthority()));
			}
			
			return roles;
		}

		@Override
		public String getPassword() {
			return this.user.getPassword();
		}

		@Override
		public String getUsername() {
			return this.user.getUsername();
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
	}

}
