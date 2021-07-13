package net.teraware.manager;

import java.io.FileNotFoundException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import net.teraware.util.Config;
import net.teraware.util.ConfigHelper;

@WebListener
public class DatabaseInit implements ServletContextListener {

	private Config config;
	private ConfigHelper cfgHelper;

	public void contextInitialized(ServletContextEvent event) {
		this.cfgHelper = new ConfigHelper(event.getServletContext());
		try {
			this.config = cfgHelper.readConfig();
			DatabaseManager.initInstance(
				this.config.getHost(),
				this.config.getUsername(),
				this.config.getPassword(),
				this.config.getPort(),
				false
			);
		} catch (FileNotFoundException e) {
			System.out.println("Config file not found");
		}
	}
}
