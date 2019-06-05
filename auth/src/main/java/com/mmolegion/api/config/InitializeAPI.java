package com.mmolegion.api.config;

import com.mmolegion.core.config.AppProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class InitializeAPI implements WebApplicationInitializer {

    private static final Logger logger = LogManager.getLogger(InitializeAPI.class);
    private static final String filename = "application.properties";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AppProperties properties = AppProperties.getInstance();
        AppProperties.setProperties(readProperties());
    }

    private Properties readProperties() {
        Properties properties = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream(filename);

        try {
            properties.load(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public void reloadProperties() {
        AppProperties.setProperties(readProperties());
    }

}
