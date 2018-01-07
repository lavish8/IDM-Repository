package com.identity.manager.test.service.util;

import com.identity.manager.enums.DomainObjectEnum.COMPANY_TYPE;
import com.identity.manager.web.domain.CompanyPojo;

public class CompanyUtil {
	/**
	 * Non instantiable.
	 */
	private CompanyUtil() {
		throw new AssertionError("Non instantiable");
	}

	public static CompanyPojo createCompany(String name, String code, COMPANY_TYPE type) {
		CompanyPojo company = new CompanyPojo();
		company.setCode(code);
		company.setName(name);
		company.setCompanyType(type);
		company.setDescription(name +" of type "+ type);
		return company;
	}
}
