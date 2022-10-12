import appenders.Appender;
import appenders.ConsoleAppender;
import appenders.FileAppender;
import layouts.Layout;
import layouts.SimpleLayout;
import layouts.XmlLayout;
import loggers.Logger;
import loggers.MessageLogger;
import loggers.ReportLevel;
import utilities.File;
import utilities.LogFile;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       int countAppenders = Integer.parseInt(scanner.nextLine());

        Map<Appender, ReportLevel> appendersMap = new LinkedHashMap<>();

       while (countAppenders-- > 0){
           String[] tokens = scanner.nextLine().split("\\s+");
           String appenderType = tokens[0];
           String layoutType = tokens[1];

           ReportLevel reportLevel = tokens.length==3
                                    ? ReportLevel.valueOf(tokens[2])
                                    : ReportLevel.INFO;

           Layout layout = createLayout(layoutType);
           Appender appender = creatAppender(appenderType, layout);
           appendersMap.put(appender, reportLevel);
       }

       String line = scanner.nextLine();
       Logger logger = new MessageLogger(appendersMap);

       while (!line.equals("END")){
           String [] tokens = line.split("\\|");
           ReportLevel reportLevel = ReportLevel.valueOf(tokens[0]);
           String dateAndTime = tokens[1];
           String message = tokens[2];

           switch (reportLevel){
               case INFO : logger.logInfo(dateAndTime, message);
               break;
               case WARNING: logger.logWarning(dateAndTime, message);
               break;
               case ERROR: logger.logError(dateAndTime, message);
               break;
               case CRITICAL: logger.logCritical(dateAndTime, message);
               break;
               case FATAL: logger.logFatal(dateAndTime, message);
               break;
           }

           line = scanner.nextLine();
       }
        System.out.println(logger.toString());


    }

    private static Appender creatAppender(String appenderType, Layout layout) {

       switch (appenderType){
            case "ConsoleAppender": return new ConsoleAppender(layout);
            case "FileAppender": return new FileAppender(layout, new LogFile());
        }
        throw new IllegalStateException("Unexpected appender type: "+ appenderType);
    }

    private static Layout createLayout(String layoutType) {
        return layoutType.equals("SimpleLayout")
                ? new SimpleLayout()
                : new XmlLayout();
    }
}
