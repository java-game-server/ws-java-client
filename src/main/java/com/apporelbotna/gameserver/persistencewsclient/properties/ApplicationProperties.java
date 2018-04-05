package com.apporelbotna.gameserver.persistencewsclient.properties;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ApplicationProperties
{
	private static final Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);

	private static String version;
	private static String name;

	private static String serverUrl;

	private ApplicationProperties()
	{
		throw new UnsupportedOperationException("ApplicationProperties must not be instantiated!");
	}

	static
	{
		ResourceBundle bundle = ResourceBundle
				.getBundle("com.apporelbotna.gameserver.pongserver.properties.application");

		version = bundle.getString("version");
		name = bundle.getString("name");

		serverUrl = bundle.getString("server.url");
	}

	public static String getVersion()
	{
		return version;
	}

	public static String getName()
	{
		return name;
	}

	public static String getServerUrl()
	{
		return serverUrl;
	}
}
