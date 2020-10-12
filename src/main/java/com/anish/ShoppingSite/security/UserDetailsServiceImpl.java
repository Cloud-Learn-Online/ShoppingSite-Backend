package com.anish.ShoppingSite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anish.ShoppingSite.model.Users;
import com.anish.ShoppingSite.repository.UsersRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsersRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user =userRepo.findByEmail(username);
		return new UserDetailsImpl(user);
	}

}
