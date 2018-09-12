package com.identity.manager.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.identity.manager.persistence.domain.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 */
@Repository
@Transactional(readOnly = true)
public interface UserDao extends JpaRepository<User, Long> {

	/**
	 * Find by login.
	 *
	 * @param login
	 *            the login
	 * @return User
	 */
	User findByLogin(String login);

	/**
	 * Find by email.
	 *
	 * @param email
	 *            the email
	 * @return Optional.empty() when the query executed does not produce a result.
	 *         Throws an IllegalArgumentException when the emailAddress handed to
	 *         the method is null.
	 */
	
	Optional<User> findByEmail(String email);

	/**
	 * Update user password.
	 *
	 * @param userId
	 *            the user id
	 * @param password
	 *            the password
	 */
	@Transactional
	@Modifying
	@Query("update User u set u.password = :password where u.id = :userId")
	void updateUserPassword(@Param("userId") long userId, @Param("password") String password);
}
