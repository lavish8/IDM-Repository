package com.identity.manager.web.controller;

import java.util.Locale;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identity.manager.constants.UserConstants;
import com.identity.manager.service.UserService;
import com.identity.manager.web.domain.UserPojo;
import com.identity.platform.auth.constant.Constants;
import com.identity.platform.service.handler.AbstractServiceHandler;
import com.identity.platform.utils.HeaderdataManager;
import com.identity.platform.utils.client.AbstractResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(UserConstants.V1_USER)
@Api(value = "userHandler", description = "manage user for proflling")
public class UserController extends AbstractServiceHandler {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	@Qualifier(value = "iamUserServiceImpl")
	private UserService<UserPojo, Long> userService;

	@PostMapping
	@ApiOperation(value = "manage user", response = AbstractResponse.class)
	public AbstractResponse create(@Valid @RequestBody UserPojo user, @RequestHeader HttpHeaders httpHeaders) {
		LOG.debug("content of user object {} #############", user);
		HeaderdataManager.setHeader(httpHeaders.toSingleValueMap());
		UserPojo newUser  = userService.save(user);
		LOG.debug("User has created successfullly for login {} ", user.getLogin());
		return handleResponse(newUser, HttpStatus.CREATED);
	}

	@GetMapping
	@ApiOperation(value = "search user", response = AbstractResponse.class)
	public AbstractResponse fetch(@Valid @ModelAttribute UserPojo user, @RequestHeader HttpHeaders httpHeaders) {
		LOG.debug("content of user object {} #############", user);
		HeaderdataManager.setHeader(httpHeaders.toSingleValueMap());
		return handleResponse(userService.find(user));
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "search user by user ID", response = AbstractResponse.class)
	public AbstractResponse fetchById(@PathVariable() Long id, @RequestHeader HttpHeaders httpHeaders) {
		LOG.debug("user id {} #############", id);
		HeaderdataManager.setHeader(httpHeaders.toSingleValueMap());
		return handleResponse(userService.findOne(id));
	}

	@DeleteMapping
	@ApiOperation(value = "delete user", response = AbstractResponse.class)
	public AbstractResponse delete(@Valid @ModelAttribute UserPojo user, @RequestHeader HttpHeaders httpHeaders) {
		LOG.debug("content of user object {} #############", user);
		HeaderdataManager.setHeader(httpHeaders.toSingleValueMap());
		userService.delete(user);
		return handleResponse(messageSource.getMessage(Constants.DESCMessage.OBJECT_DELETED.value(),
				new Object[] { UserConstants.USER }, Locale.getDefault()));
	}

	@DeleteMapping(value = "id")
	@ApiOperation(value = "delete user by id", response = AbstractResponse.class)
	public AbstractResponse deleteById(@PathParam(value = "id") String userId, @RequestHeader HttpHeaders httpHeaders) {
		LOG.debug("content of user object {} #############", userId);
		HeaderdataManager.setHeader(httpHeaders.toSingleValueMap());
		userService.delete(userId);
		return handleResponse(messageSource.getMessage(Constants.DESCMessage.OBJECT_DELETED_BY_ID.value(),
				new Object[] { UserConstants.USER, userId }, Locale.getDefault()));
	}
}
