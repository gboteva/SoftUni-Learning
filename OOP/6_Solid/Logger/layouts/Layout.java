package layouts;

import loggers.ReportLevel;

public interface Layout {

    String formatMessage(String timeStamp, ReportLevel reportLevel, String message);

}
