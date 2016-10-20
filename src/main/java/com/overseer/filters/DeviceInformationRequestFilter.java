package com.overseer.filters;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.overseer.device.*;
import com.overseer.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Provider
public class DeviceInformationRequestFilter implements ContainerRequestFilter {

	private static final ObjectMapper mapper = new ObjectMapper();
	private static Logger log = Logger.getLogger(DeviceInformationRequestFilter.class);
	@Autowired
	private ApplicationContext appContext;

	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		String deviceInfoHeaderValue = context.getHeaders().getFirst("X-Device-Information");
		if (StringUtil.isNullOrWhitespace(deviceInfoHeaderValue))
			return;
		try {
			processDeviceInformationFromHeader(deviceInfoHeaderValue);
		} catch (Exception ex) {
			log.error(String.format(
					"Exception happened while processing device information \n Device Information Header: %s \n %s",
					deviceInfoHeaderValue, ex.toString()));
		}

	}

	private void processDeviceInformationFromHeader(String deviceInfoHeaderValue)
			throws JsonParseException, JsonMappingException, IOException {
		String deviceInformationJson = URLDecoder.decode(deviceInfoHeaderValue,
				java.nio.charset.StandardCharsets.UTF_8.toString());
		DeviceInformationHeaderData deviceHeaderData = mapper.readValue(deviceInformationJson, DeviceInformationHeaderData.class);
		IDeviceInformationDao deviceInformationDao = (IDeviceInformationDao) appContext.getBean("deviceInformationDao");
		DeviceInformation deviceInfo = deviceInformationDao.getDeviceInformationByDeviceId(deviceHeaderData.getDeviceId());
		if (deviceInfo == null) {
			deviceInformationDao.insertDeviceInformation(this.getDeviceInformationFromHeaderData(deviceHeaderData));
		} else {
			Set<BrowserInformation> filteredBrowsers = deviceInfo.getBrowsers().stream()
					.filter(browser -> !browser.getBrowserId().equals(deviceHeaderData.getBrowserInformation().getBrowserId()))
					.collect(Collectors.toSet());
			filteredBrowsers.addAll(this.getDeviceInformationFromHeaderData(deviceHeaderData).getBrowsers());
			deviceInfo.setBrowsers(filteredBrowsers);
			deviceInformationDao.updateDeviceInformation(deviceInfo);
		}
	}
	
	private DeviceInformation getDeviceInformationFromHeaderData(DeviceInformationHeaderData deviceHeaderData){
		DeviceInformation deviceInfo = new DeviceInformation();
		deviceInfo.setDeviceId(deviceHeaderData.getDeviceId());
		deviceInfo.setDeviceType(deviceHeaderData.getDeviceType());
		deviceInfo.setOperatingSystem(deviceHeaderData.getOperatingSystem());
		deviceInfo.setOperatingSystemVersion(deviceHeaderData.getOperatingSystemVersion());
		ScreenInformation screenInformation = deviceHeaderData.getScreenInformation();
		if(screenInformation != null) screenInformation.setDeviceInformation(deviceInfo);
		deviceInfo.setScreenInformation(screenInformation);
		MobileSpecificInformation mobileSpecificInformation = deviceHeaderData.getMobileSpecificInformation();
		if(mobileSpecificInformation != null) mobileSpecificInformation.setDeviceInformation(deviceInfo);
		deviceInfo.setMobileSpecificInformation(mobileSpecificInformation);
		deviceInfo.setTimezone(deviceHeaderData.getTimezone());
		deviceInfo.setCreatedTimestamp(new Date());
		Set<BrowserInformation> browsers = new HashSet<BrowserInformation>();
		BrowserInformation browserInformation = deviceHeaderData.getBrowserInformation();
		browsers.add(browserInformation);
		deviceInfo.setBrowsers(browsers);
		return deviceInfo;
		
	}
	
	private static class DeviceInformationHeaderData{
		private String deviceId;
		private DeviceType deviceType;
		private String operatingSystem;
		private Date createdTimestamp;
		private Date lastUpdatedTimestamp;
		private String operatingSystemVersion;
		private String timezone;
		private BrowserInformation browserInformation;
		private ScreenInformation screenInformation;
		private MobileSpecificInformation mobileSpecificInformation;

		@SuppressWarnings("unused")
		public DeviceInformationHeaderData(){
			
		}
		
		public String getDeviceId() {
			return deviceId;
		}

		@SuppressWarnings("unused")
		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

		public DeviceType getDeviceType() {
			return deviceType;
		}

		@SuppressWarnings("unused")
		public void setDeviceType(DeviceType deviceType) {
			this.deviceType = deviceType;
		}

		public String getOperatingSystem() {
			return operatingSystem;
		}

		@SuppressWarnings("unused")
		public void setOperatingSystem(String operatingSystem) {
			this.operatingSystem = operatingSystem;
		}

		@SuppressWarnings("unused")
		public Date getCreatedTimestamp() {
			return createdTimestamp;
		}

		@SuppressWarnings("unused")
		public void setCreatedTimestamp(Date createdTimestamp) {
			this.createdTimestamp = createdTimestamp;
		}

		@SuppressWarnings("unused")
		public Date getLastUpdatedTimestamp() {
			return lastUpdatedTimestamp;
		}

		@SuppressWarnings("unused")
		public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
			this.lastUpdatedTimestamp = lastUpdatedTimestamp;
		}

		public String getOperatingSystemVersion() {
			return operatingSystemVersion;
		}

		@SuppressWarnings("unused")
		public void setOperatingSystemVersion(String operatingSystemVersion) {
			this.operatingSystemVersion = operatingSystemVersion;
		}
		
		public String getTimezone() {
			return timezone;
		}

		@SuppressWarnings("unused")
		public void setTimezone(String timezone) {
			this.timezone = timezone;
		}

		public BrowserInformation getBrowserInformation() {
			return browserInformation;
		}

		@SuppressWarnings("unused")
		public void setBrowserInformation(BrowserInformation browserInformation) {
			this.browserInformation = browserInformation;
		}

		public ScreenInformation getScreenInformation() {
			return screenInformation;
		}

		@SuppressWarnings("unused")
		public void setScreenInformation(ScreenInformation screenInformation) {
			this.screenInformation = screenInformation;
		}

		public MobileSpecificInformation getMobileSpecificInformation() {
			return mobileSpecificInformation;
		}

		@SuppressWarnings("unused")
		public void setMobileSpecificInformation(MobileSpecificInformation mobileSpecificInformation) {
			this.mobileSpecificInformation = mobileSpecificInformation;
		}

	}

}
