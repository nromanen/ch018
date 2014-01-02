package com.ch018.library.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Person.Role;

@Service
public class LibraryUserDetailsService implements UserDetailsService {

	@Autowired
	PersonDao personDao;
	
	@Transactional
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		Person user = personDao.getByEmail(login);
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(user.getRole().toString()));
		
		User u = new User(user.getEmail(), user.getPassword(), user.getConfirm(), true, true, true, roles);
		System.out.println(u.getPassword());
		System.out.println(u.getAuthorities());
		return u;
	}

}
