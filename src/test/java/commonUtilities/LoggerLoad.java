package commonUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerLoad {

	public static Logger LoggerLoad = LogManager.getLogger();

	public static void logInfo(String message) {

		LoggerLoad.info(message);

	}

	public static void warn(String message) {

		LoggerLoad.warn(message);

	}

	public static void error(String message) {

		LoggerLoad.error(message);

	}

	public static void fatal(String message) {

		LoggerLoad.fatal(message);

	}

	public static void debug(String message) {

		LoggerLoad.debug(message);

	}

}
