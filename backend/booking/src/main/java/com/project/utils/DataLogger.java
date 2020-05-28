package com.project.utils;

import java.util.logging.Logger;

public class DataLogger {
	private static final Logger LOGGER = Logger.getLogger(DataLogger.class.getName());

	/* ----- CONSTRUCTORS ----- */
	private DataLogger() {
		super();
	}

	/* ----- METHODS ----- */
	public static void printInfo(String messagge) {
		LOGGER.info(messagge);
	}

	public static void printWarning(String messagge) {
		LOGGER.warning(messagge);
	}

	public static void printError(String messagge) {
		LOGGER.severe(messagge);
	}
}
