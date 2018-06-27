/**
 * 
 */
package com.identity.manager.web.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identity.manager.constant.WebConstants;
import com.identity.manager.service.RoleService;
import com.identity.manager.service.impl.RoleServiceImpl;
import com.identity.manager.web.domain.RolePojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author maheshs1
 *
 */
@RestController
@RequestMapping(value = WebConstants.V1_ROLE_URI)
@Api(value = "Role API", description = "Role service handler for application")
public class RoleController {

	private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

	private RoleService<RolePojo, Long> roleService = new RoleServiceImpl();

	@PostMapping
	@ApiOperation(value = "create role for your company", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully role created"),
			@ApiResponse(code = 401, message = "You are not authorized to create the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<RolePojo> save(@RequestBody RolePojo role) {
		LOG.debug("################# content of role page {} #############", role);
		RolePojo newCreatedRole = roleService.save(role);
		return new ResponseEntity<>(newCreatedRole, HttpStatus.OK);
	}
}
