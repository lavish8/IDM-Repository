/**
 * 
 */
package com.identity.manager.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.identity.manager.config.ModelMapperConfig;
import com.identity.manager.exception.InvalidDataException;
import com.identity.manager.helper.RoleHelper;
import com.identity.manager.persistence.dao.CompanyDao;
import com.identity.manager.persistence.dao.RoleDao;
import com.identity.manager.persistence.domain.Company;
import com.identity.manager.persistence.domain.Role;
import com.identity.manager.service.RoleService;
import com.identity.manager.web.domain.RolePojo;

/**
 * @author maheshs1
 *
 */
@Component
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService<RolePojo, Long> {
	
	private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private ModelMapperConfig modelMapper;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleHelper helper;
	

	/* (non-Javadoc)
	 * @see com.identity.manager.service.CrudService#save(java.lang.Object)
	 */
	@Override
	@Transactional
	public <S extends RolePojo> S save(S entity) {
		Role localRole = roleDao.findByName(entity.getName());
		if(localRole != null && !entity.isContinue()){
			log.info("role name is already exist Nothing will be done. ", entity.getName());
			throw new InvalidDataException("Role name is already exist");
		}		
		
		Company userCompany = companyDao.findByCode(entity.getCompany());
		if (userCompany == null) {
			throw new InvalidDataException("Company not found for value of " + entity.getCompany());
		}
		Role role = modelMapper.map(entity, Role.class);
		role.setCompany(userCompany);
		helper.exteract(entity, role);
		
		localRole = roleDao.save(role);
		
		return (S) modelMapper.map(localRole, RolePojo.class);
	}

	/**
	 * exteract
	 * RoleServiceImpl
	 * @param entity
	 * @param role
	 */
	

	/* (non-Javadoc)
	 * @see com.identity.manager.service.CrudService#save(java.lang.Iterable)
	 */
	@Override
	public <S extends RolePojo> Iterable<S> save(Iterable<S> entities) {
		entities.forEach(entity->save(entity));
		return null;
	}

	/* (non-Javadoc)
	 * @see com.identity.manager.service.CrudService#findAll(java.lang.Iterable)
	 */
	@Override
	public Iterable<RolePojo> findAll(Iterable<RolePojo> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.identity.manager.service.CrudService#find(java.io.Serializable)
	 */
	@Override
	public RolePojo find(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.identity.manager.service.CrudService#delete(java.io.Serializable)
	 */
	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.identity.manager.service.CrudService#delete(java.lang.Object)
	 */
	@Override
	public void delete(RolePojo entity) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.identity.manager.service.CrudService#delete(java.lang.Iterable)
	 */
	@Override
	public void delete(Iterable<? extends RolePojo> entities) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.identity.manager.service.CrudService#deleteAll()
	 */
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.identity.manager.service.RoleService#findByName(java.lang.String)
	 */
	@Override
	public RolePojo findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
