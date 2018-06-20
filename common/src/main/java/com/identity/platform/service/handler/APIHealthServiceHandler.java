package com.identity.platform.service.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identity.platform.service.AbstractHealthService;
import com.identity.platform.utils.client.AbstractResponse;
import com.identity.platform.utils.error.exception.PlatformException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = APIHealthServiceHandler.HEALTH, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Health")
public class APIHealthServiceHandler extends AbstractServiceHandler {

	protected static final String HEALTH = "/health";

	@Autowired
	private AbstractHealthService healthService;
	
	/**
     * Return the health statistics to show the status of service.
     *
     * @return health of the service
     * @throws PlatformException
     *             the platform exception
     */
    @ApiOperation(value = "Get Health")
    @GetMapping
    public final AbstractResponse createHealthStats() throws PlatformException {
        return handleResponse(this.healthService.health());
    }

}
