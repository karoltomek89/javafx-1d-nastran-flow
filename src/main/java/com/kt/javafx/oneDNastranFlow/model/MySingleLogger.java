package com.kt.javafx.oneDNastranFlow.model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class MySingleLogger {

    private static class SingletonHelper {
        private static final MySingleLogger INSTANCE = new MySingleLogger();
    }

    public static MySingleLogger getInstance() {
        return SingletonHelper.INSTANCE;
    }

    LoggerContext ctx;
    ConfigurationBuilder<BuiltConfiguration> builder;
    Logger logger;

    private MySingleLogger() {}

    public void startLogging(String pathToBDF) {

        builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        builder.setStatusLevel(Level.DEBUG);
        builder.setConfigurationName("FileBuilder");
        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d %-5level: %msg%n");
        AppenderComponentBuilder appenderBuilder = builder.newAppender("file", "File")
                .addAttribute("fileName", pathToBDF.replace(".bdf", "_CHBDYP_CONVM.log"))
                .addAttribute("append", false)
                .add(layoutBuilder);
        builder.add(appenderBuilder);
        builder.add(builder.newLogger("TestLogger", Level.DEBUG)
                .add(builder.newAppenderRef("file"))
                .addAttribute("additivity", false));

        ctx = Configurator.initialize(builder.build());

        logger = ctx.getLogger("TestLogger");
    }

    public void stopLogging() {
        ctx.close();
    }

    public Logger getLogger() {
        return logger;
    }
}
