package com.identity.manager.utils;

public class GeneralBaseConstants {

	private GeneralBaseConstants() {
		throw new IllegalStateException("Utility class");
	}

	/* for temporary exception codes */

	public static final String DATASYSEXC_CODE = "DSXO001";
	public static final String JDBCSYSEXC_CODE = "DSXO003";
	public static final String CONSTRAINT_VIOLATION_SYSEXC_CODE = "DSXO002";
	public static final String SYSEXC_CODE = "SXO001";
	public static final String NOHANDLER_FOUND_CODE = "NHX404";
	public static final String GLOBAL_EXCEPTION_CODE = "EX500";
	public static final String BUSINESS_EXCEPTION_CODE = "BX_001";
	public static final String SERVICE_EXCEPTION_CODE = "SX_001";	
	
	

	public static final String DATE_FORMAT = "dd/MM/yyyy-hh:mm:ss:SSS";
	public static final Integer RANDOM_INCREMENTOR = 10000;
}
