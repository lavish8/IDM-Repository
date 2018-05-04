package com.identity.manager.web.controller;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identity.manager.constants.Constants;
import com.identity.manager.constants.UserConstants;
import com.identity.manager.service.UserService;
import com.identity.manager.web.domain.UserPojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("user")
@Api(value="userHandler", description="manage user for proflling")
@ApiResponses(value = {
        @ApiResponse(code = 401, message = Constants.NOT_AUTHORIZED),
        @ApiResponse(code = 403, message = Constants.FORBIDDEN),
        @ApiResponse(code = 404, message = Constants.NOT_FOUND)
})
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService<UserPojo,Long> userService;

	/**
	 * create post.
	 *
	 * @param manage user
	 * @return the user
	 */
	@PostMapping
	@ApiOperation(value = "manage user", response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = UserConstants.USER_CREATED)
	})
	public ResponseEntity<String> create(@RequestBody UserPojo user) {
		LOG.debug("content of user object {} #############", user);
		userService.save(user);
		return new ResponseEntity<>("Your responce has been registered successfully", HttpStatus.OK);
	}
	
	@GetMapping
	@ApiOperation(value = "search user", response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = UserConstants.USER_SEARCH)
	})
	public ResponseEntity<String> fetch(@ModelAttribute UserPojo user) {
		LOG.debug("content of user object {} #############", user);
		userService.find(user);
		return new ResponseEntity<>("Your responce has been registered successfully", HttpStatus.OK);
	}
	
	@GetMapping
	@ApiOperation(value = "search user by user ID", response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = UserConstants.USER_SEARCH_BY_ID)
	})
	public ResponseEntity<String> fetchById(@PathParam(value = "id") String id, @ModelAttribute UserPojo user) {
		LOG.debug("content of user object {} #############", user);
		userService.find(id);
		return new ResponseEntity<>("Your responce has been registered successfully", HttpStatus.OK);
	}
	
	@DeleteMapping
	@ApiOperation(value = "delete user", response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = UserConstants.USER_DELETED)
	})
	public ResponseEntity<String> delete(@ModelAttribute UserPojo user) {
		LOG.debug("content of user object {} #############", user);
		userService.delete(user);
		return new ResponseEntity<>("Your responce has been registered successfully", HttpStatus.OK);
	}
	
	@DeleteMapping
	@ApiOperation(value = "delete user by id", response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = UserConstants.USER_DELETED_BY_ID)
	})
	public ResponseEntity<String> deleteById(@PathParam(value = "id") String userId ) {
		LOG.debug("content of user object {} #############", userId);
		userService.delete(userId);
		return new ResponseEntity<>("Your responce has been registered successfully", HttpStatus.OK);
	}
}
