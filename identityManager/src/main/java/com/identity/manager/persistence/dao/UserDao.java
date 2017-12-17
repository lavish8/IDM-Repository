package com.identity.manager.persistence.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.identity.manager.persistence.domain.User;

@Repository
@Transactional(readOnly = true)
public interface UserDao extends CrudRepository<User, Long> {

	User findByLogin(String login);
	
	User findByEmail(String email);

	@Transactional
    @Modifying
    @Query("update User u set u.password = :password where u.id = :userId")
    void updateUserPassword(@Param("userId") long userId, @Param("password") String password);
}
