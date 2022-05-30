package com.kodgemisi.filter;

import com.kodgemisi.usermanagement.User;
import com.kodgemisi.usermanagement.UserDao;
import com.kodgemisi.usermanagement.UserDaoImpl;
import com.kodgemisi.usermanagement.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterApiImpl implements FilterApi {
	
	private UserService userService;
	
	public FilterApiImpl(UserService userService) {
		super();
		this.userService = userService;
	}
	
	private User user;

	public FilterApiImpl(UserService userService, User user) {
		super();
		this.userService = userService;
		this.user = user;
	}

	@Override
	public List<User> unverifiedUnder18() {
		//FIXME currently returns all the users unfiltered, you should fix this method
		// If you are not sure how to implement this method, please refer to the Javadoc or the FilterApi interface
			List<User> users = userService.list().stream()
					.filter(u -> u.getAge()<18)
					.filter( u-> u.isVerified()==false)
					.collect(Collectors.toList());
			return users;
	}

	@Override
	public List<User> verifiedWithTrPrimaryPhone() {
		//FIXME currently returns all the users unfiltered, you should fix this method
		// If you are not sure how to implement this method, please refer to the Javadoc or the FilterApi interface
		List<User> users = userService.list().stream()
				.filter(u -> u.isVerified()==true)
				.filter( u-> u.getProfile().getPrimaryPhone().toString().startsWith("+90"))
				.collect(Collectors.toList());
		return users;
	}
}
