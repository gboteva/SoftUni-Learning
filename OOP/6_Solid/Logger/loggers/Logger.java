package loggers;

public interface Logger {

    void logInfo (String dateAndTime, String message);

    void logWarning (String dateAndTime, String message);
    void logError (String dateAndTime, String message);

    void logCritical (String dateAndTime, String message);

    void logFatal (String dateAndTime, String message);

}
