package com.projects.business_trip_management.service;

import java.util.HashSet;
import java.util.Set;

import com.projects.business_trip_management.repository.RoleRepository;
import com.projects.business_trip_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projects.business_trip_management.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Account not found");
		}
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));		
		
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
	}
}
