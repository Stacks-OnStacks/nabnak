package com.revature.nabnak.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.Properties;

// TODO: IMPLEMENT ME
public class CustomLogger {
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static CustomLogger logger;
    private final boolean printToConsole;
    private final Writer logWriter;

    // Singleton - there is only a single place where the class can be instaniated
    private CustomLogger(boolean printToConsole) {
        Writer writer = null;
        this.printToConsole = printToConsole;
        try {
            writer = new FileWriter("resources/log.txt", true);
        } catch (IOException e) {
            printMessageToConsole("ERROR", "Could not open connection to file. Only printing logs to console.");
        }

        this.logWriter = writer;
    }

    // TODO: What's with the logger == null?
    public static CustomLogger getLogger(boolean printToConsole) {
        // Lazy singleton
        if(logger == null) {
            logger = new CustomLogger(printToConsole);
        }

        return logger;
    }

    // Think about the different thresholds and assigning methods to them
    public void info(String message, Object...extra) {
        String formattedMessage = formatMessage("INFO", String.format(message, extra));
        logMessageToFile(formattedMessage);
        if(printToConsole) printMessageToConsole("INFO", formattedMessage);
    }
    public void warn(String message, Object...extra) {
        String formattedMessage = formatMessage("WARN", String.format(message, extra));
        logMessageToFile(formattedMessage);
        if(printToConsole) printMessageToConsole("WARN", formattedMessage);
    }
    public void error(String message, Object...extra) {
        String formattedMessage = formatMessage("ERROR", String.format(message, extra));
        logMessageToFile(formattedMessage);
        if(printToConsole) printMessageToConsole("ERROR", formattedMessage);
    }
    public void fatal(String message, Object...extra) {
        String formattedMessage = formatMessage("FATAL", String.format(message, extra));
        logMessageToFile(formattedMessage);
        if(printToConsole) printMessageToConsole("FATAL", formattedMessage);
    }

    private void logMessageToFile(String formattedMessage) {
        if (logWriter != null) {
            try {
                logWriter.write(formattedMessage + "\n");
                logWriter.flush();
            } catch (IOException e) {
                printMessageToConsole("ERROR", "Could not write message to file");
            }
        }
    }
    private void printMessageToConsole(String level, String message) {
        switch (level) {
            case "INFO":
                System.out.println(ANSI_GREEN + message + ANSI_RESET);
                break;
            case "WARN":
                System.out.println(ANSI_PURPLE + message + ANSI_RESET);
                break;
            case "ERROR":
            case "FATAL":
                System.out.println(ANSI_RED + message + ANSI_RESET);
                break;
        }

    }

    private String formatMessage(String level, String message) {
        return String.format("[%s] %s at %s", level, message, LocalDateTime.now());
    }

    public void log(String message, Object... extra) { // This is variable arguments handles x amount of arguments passed after the String message
        try(Writer logWriter = new FileWriter("resources/log.txt", true);) {

            String messFormated = String.format(message, extra);
            logWriter.write(messFormated + "\n");

            if(printToConsole) {
                System.out.println(ANSI_GREEN + message + ANSI_RESET);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
