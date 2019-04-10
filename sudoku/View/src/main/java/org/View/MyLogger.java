package org.View;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    private static FileHandler fileTxt;
    private static ConsoleHandler consoleTxt;
    private static SimpleFormatter formatterTxt;


    public static void setup(final ResourceBundle languageBundle) throws FileException {

        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        logger.setLevel(Level.INFO);
        try {
            fileTxt = new FileHandler("Logging.txt");
            consoleTxt = new ConsoleHandler();
        } catch (IOException e) {
            throw new FileException(languageBundle.getString("file"), e);
        }

        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        consoleTxt.setFormatter(formatterTxt);
        
        logger.addHandler(fileTxt);
        logger.addHandler(consoleTxt);
    }
}