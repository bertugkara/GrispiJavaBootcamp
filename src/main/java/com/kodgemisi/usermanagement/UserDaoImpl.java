package com.kodgemisi.usermanagement;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {

	private final AtomicLong userIdSequence = new AtomicLong(1L);

	private final Map<Long, User> userMap = new ConcurrentHashMap<>();

	@Override
	public User create(User user) {

		if (user == null) {
			throw new IllegalArgumentException("User cannot be null!");
		}

		if (!user.isNew()) {
			throw new IllegalArgumentException("You can create only new users! This user object has an id: '%s'!".formatted(user.getId()));
		}

		final long userId = userIdSequence.getAndIncrement();
		user.setId(userId);

		userMap.put(userId, user);
		return user;
	}

	@Override
	public Optional<User> find(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("Id cannot be null!");
		}

		return Optional.ofNullable(userMap.get(id));
	}

	@Override
	public List<User> findAll() {
		return userMap.values().stream().sorted(Comparator.comparingLong(User::getId)).collect(Collectors.toList());
	}

	@Override
	public User update(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User cannot be null!");
		}

		if (user.isNew()) {
			throw new IllegalArgumentException("You can only update existing users (users with id). This user has no id");
		}
		
		//Here is the issue, In the previous form return userMap.put(user.getId(), user);  
		// we do not returning the user, we return usermap function. So that it wont change values.
		
		userMap.put(user.getId(), user);
		return user;
				
	}

	@Override
	public void delete(Long id) {
		throw new UnsupportedOperationException("Not implemented!");
	}

}
