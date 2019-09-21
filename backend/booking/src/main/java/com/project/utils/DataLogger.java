package com.project.utils;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class DataLogger {
	private static final Logger LOGGER = Logger.getLogger(DataLogger.class.getName());

	/* ----- CONSTRUCTORS ----- */
	public DataLogger() {
		super();
	}

	/* ----- METHODS ----- */
	public void printInfo(String messagge) {
		LOGGER.info(messagge);
	}

	public void printWarning(String messagge) {
		LOGGER.warning(messagge);
	}

	public void printError(String messagge) {
		LOGGER.severe(messagge);
	}
}
