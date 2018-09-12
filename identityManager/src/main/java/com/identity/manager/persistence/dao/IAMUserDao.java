package com.identity.manager.persistence.dao;

import java.util.concurrent.Future;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseSortingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.identity.manager.persistence.domain.IAMUser;

import reactor.core.publisher.Mono;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAMUserDao. IAMUserDao supports N1QL which is the default way
 * of doing queries and will allow you to fully derive queries from a method
 * name.
 * 
 * Prerequisite is to have a N1QL-compatible cluster and to have created a PRIMARY INDEX on the bucket
 */

@Repository
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "iamUsers")
public interface IAMUserDao extends ReactiveCouchbaseSortingRepository<IAMUser, Long> {

	/**
	 * Find by login.
	 *
	 * @param login
	 *            the login
	 * Optional<IAMUser> findByLogin(String login);
	 * @return Returns Optional.empty() when the query executed does not produce a result
	 * Throws an IllegalArgumentException when the login handed to the method is null
	 */
	Mono<IAMUser> findByLogin(String login);

	/**
	 * Find by email.
	 *
	 * @param email
	 *            the email
	 * @return Returns null when the query executed does not produce a result. Also
	 *         accepts null as the value for email.
	 */
	@Nullable
	Mono<IAMUser> findByEmail(@Nullable String email);
	
	/**
	 * Update user password Async manner This means the method returns immediately upon
	 * invocation while the actual query execution occurs in a task that has been
	 * submitted to a Spring TaskExecutor
	 *
	 * @param userId
	 *            the user id
	 * @param password
	 *            the password
	 */
	@Async
	@Query("update User u set u.password = :password where u.id = :userId")
	Future<IAMUser> password (@Param("userId") long userId, @Param("password") String password);

}
