package com.identity.manager.test.dao.util;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.identity.manager.enums.DomainObjectEnum;
import com.identity.manager.persistence.domain.Company;

public class CompanyUtil {

	private CompanyUtil() {
		throw new AssertionError("Non instantiable");
	}
	
	public static Company createCompany(String name, String code){
		Company company = new Company();
		company.setName(name);
		company.setCode(code);
		company.setCompanyType(DomainObjectEnum.PARENT_COMPANY_TYPE.getValue());
		company.setDescription(DomainObjectEnum.PARENT_COMPANY_TYPE.getValue()+" of "+code);
		company.setObsoleteDate(LocalDate.now());
		company.setStatusDate(LocalDateTime.now());
		return company;
	}

}
