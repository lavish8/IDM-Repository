/**
 * 
 */
package com.identity.manager.helper;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.identity.manager.enums.ApplicationEnum;
import com.identity.manager.enums.DomainObjectEnum;
import com.identity.manager.persistence.dao.ApplicationDao;
import com.identity.manager.persistence.dao.EntityTypeDao;
import com.identity.manager.persistence.dao.StatusDao;
import com.identity.manager.persistence.dao.UserDao;
import com.identity.manager.persistence.domain.Role;
import com.identity.manager.web.domain.RolePojo;

/**
 * @author maheshs1
 *
 */
@Component
public class RoleHelper {	
	
	private static final Logger log = LoggerFactory.getLogger(RoleHelper.class);
	
	@Autowired
	private EntityTypeDao entityTypeDao;
	
	@Autowired
	private ApplicationDao applicationDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StatusDao statusDao;
	
	public <S extends RolePojo> void exteract(S entity, Role role) {
		role.setApplication(applicationDao.findByName(ApplicationEnum.DEFALUT_APPLICATION.getValue()));
		role.setCreatedBy(userDao.findByLogin("VIVEKC"));
		role.setDescription(entity.getDescription());
		role.setEntityType(entityTypeDao.findByName(DomainObjectEnum.ENTITY_TYPE_ROLE.getValue()));
		role.setLastModifiedBy(userDao.findByLogin("VIVEKC"));
		role.setName(entity.getName());
		role.setObsoleteDate(entity.getObsoleteDate());
		role.setStatus(statusDao.findByName(DomainObjectEnum.STATUS_ACTIVE.getValue()));
		role.setStatusDate(LocalDateTime.now());
	}

}
