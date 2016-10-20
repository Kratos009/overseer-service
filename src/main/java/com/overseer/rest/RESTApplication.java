package com.overseer.rest;

import com.overseer.filters.DeviceInformationRequestFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class RESTApplication extends ResourceConfig {

	public RESTApplication() {
		packages("com.overseer.rest");
		register(JacksonFeature.class);
		register(DeviceInformationRequestFilter.class);
	} 

}
