package com.overseer.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Properties;

public class PropertiesCache {

	@Autowired
	private ApplicationContext appContext;

	private Properties configProp = null;

	private PropertiesCache() {
		IConfigurationPropertyDao configurationPropertyDao =  (IConfigurationPropertyDao) appContext.getBean("configurationPropertyDao");
		configProp = configurationPropertyDao.getConfigurationProperties();
	}

	private static class LazyHolder {
		private static final PropertiesCache INSTANCE = new PropertiesCache();
	}

	public static PropertiesCache getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String getProperty(String key) {
		return configProp.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return configProp.getProperty(key, defaultValue);
	}
}
