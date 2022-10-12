package layouts;

import loggers.ReportLevel;

public class XmlLayout implements Layout{

    @Override
    public String formatMessage(String timeStamp, ReportLevel reportLevel, String message) {
      return String.format("<log>%n" +
                "   <date>%s</date>%n" +
                "   <level>%s</level>%n" +
                "   <message>%s</message>%n" +
                "</log>%n", timeStamp, reportLevel.toString(), message);
    }
}
