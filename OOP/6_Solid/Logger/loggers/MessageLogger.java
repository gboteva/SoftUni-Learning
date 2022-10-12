package loggers;

import appenders.Appender;
import appenders.FileAppender;

import java.util.Map;

public class MessageLogger implements Logger {
    private Map<Appender, ReportLevel> appendersMap;


    public MessageLogger(Map<Appender, ReportLevel> appendersMap) {
       this.appendersMap = appendersMap;
    }

    @Override
    public void logInfo(String dateAndTime, String message) {
        logToAllAppenders(dateAndTime, message, ReportLevel.INFO);
    }
    @Override
    public void logError(String dateAndTime, String message) {
        logToAllAppenders(dateAndTime, message, ReportLevel.ERROR);
    }

    @Override
    public void logWarning(String dateAndTime, String message) {
        logToAllAppenders(dateAndTime, message, ReportLevel.WARNING);
    }

    @Override
    public void logCritical(String dateAndTime, String message) {
        logToAllAppenders(dateAndTime, message, ReportLevel.CRITICAL);
    }

    @Override
    public void logFatal(String dateAndTime, String message) {
        logToAllAppenders(dateAndTime, message, ReportLevel.FATAL);
    }

    private void logToAllAppenders(String dateAndTime, String message, ReportLevel reportLevel){
        appendersMap.entrySet().forEach(a->{
            if (a.getValue().ordinal() <= reportLevel.ordinal()){
                a.getKey().append(dateAndTime, reportLevel, message);
            }
        });

    }

    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
       builder.append("Logger info").append(System.lineSeparator());
       //Appender type: FileAppender,
        // Layout type: XmlLayout,
        // Report level: INFO,
        // Messages appended: 5, File size: 37526
        appendersMap.entrySet().forEach(a->{
            Appender appender = a.getKey();
            ReportLevel reportLevel = a.getValue();
            builder.append("Appender type: ").append(appender.getClass().getSimpleName())
                    .append(", Layout type: ").append(appender.getLayout().getClass().getSimpleName())
                    .append(", Report level: ").append(reportLevel.toString())
                    .append(", Messages appended: ").append(appender.getCounter());


            if (appender instanceof FileAppender){
                builder.append(", File size: ").append(((FileAppender)appender).getFile().getSize());
            }
             builder.append(System.lineSeparator());
        });

        return builder.toString().trim();
    }
}
