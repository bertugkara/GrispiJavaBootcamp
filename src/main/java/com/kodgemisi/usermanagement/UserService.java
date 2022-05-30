package com.kodgemisi.usermanagement;

import java.util.List;
import java.util.Optional;

public class UserService implements UserApi {

	private UserDao userDao;

	public UserService(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public User create(User user) {
		return userDao.create(user);
	}

	@Override
	public User get(Long id) {
		return userDao.find(id).orElseThrow(() -> new NoSuchUserException(id));
	}

	@Override
	public Optional<User> find(Long id) {
		return userDao.find(id);
	}

	@Override
	public List<User> list() {
		return userDao.findAll();
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

	@Override
	public User verify(Long id) {
		final User user = this.get(id);
		user.verify();
		return this.update(user);
	}
}
