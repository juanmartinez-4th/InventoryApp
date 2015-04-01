package mx.com.mindbits.argos.inventory.webapp.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class ContextListener implements ServletContextListener {
	private static final Logger LOGGER = Logger.getLogger(ContextListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.info("\n--------------------------------------------------------------------------------"
				+ "\nCONTEXT INITIALIZED"
				+ "\n--------------------------------------------------------------------------------");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.info("\n--------------------------------------------------------------------------------"
				+ "\nCONTEXT DESTROYED"
				+ "\n--------------------------------------------------------------------------------");
	}

}
