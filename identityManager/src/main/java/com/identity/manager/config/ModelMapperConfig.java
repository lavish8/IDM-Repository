package com.identity.manager.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig extends ModelMapper {

	public ModelMapperConfig() {
		super.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldMatchingEnabled(true)
				.setMatchingStrategy(MatchingStrategies.STRICT);
		// 		.setMethodAccessLevel(AccessLevel.PROTECTED); //to allow protected methods to be matched:
		//		.setFieldAccessLevel(AccessLevel.PRIVATE); //to allow private fields to be matched:
		//		.setSourceNamingConvention(NamingConventions.NONE); // to allow any source and destination property names to be eligible for matching
		//		.setSourceNameTokenizer(NameTokenizers.UNDERSCORE); // to use the Underscore name tokenizer for source and destination properties:
		//  	.setDestinationNamingConvention(NamingConventions.NONE);	
		//		.setDestinationNameTokenizer(NameTokenizers.UNDERSCORE);
	}

}
