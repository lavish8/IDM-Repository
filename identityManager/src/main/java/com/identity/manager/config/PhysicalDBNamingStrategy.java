package com.identity.manager.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class PhysicalDBNamingStrategy extends SpringPhysicalNamingStrategy {

	@Override
	public Identifier toPhysicalColumnName(Identifier name,
			JdbcEnvironment jdbcEnvironment) {
		System.out.println("**********************->"+name.getText());
		return apply(name, jdbcEnvironment);
	}
	
	private Identifier apply(Identifier name, JdbcEnvironment jdbcEnvironment) {
		if (name == null) {
			return null;
		}
		StringBuilder builder = new StringBuilder(name.getText().replace('.', '_'));
		System.out.println("******builder****************->"+name.getText());
		return getIdentifier(builder.toString(), name.isQuoted(), jdbcEnvironment);
	}
}
