package com.uber.coding_challange.food_trucks_test;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.test.web.client.LocalHostUriTemplateHandler;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FoodTruckTestConfiguration 
{
	@Bean
	public TestRestTemplate testRestTemplate(
			ObjectProvider<RestTemplateBuilder> builderProvider,
            Environment environment)
	{
		RestTemplateBuilder builder = builderProvider.getIfAvailable();
        TestRestTemplate template = builder == null 
        		? new TestRestTemplate()
                : new TestRestTemplate(builder.build());
        		
        template.setUriTemplateHandler(new LocalHostUriTemplateHandler(environment));
        return template;
	}
}
