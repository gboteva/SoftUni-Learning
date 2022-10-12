package appenders;

import layouts.Layout;
import loggers.ReportLevel;

public interface Appender {

    void append(String timeStamp, ReportLevel reportLevel, String message);
    Layout getLayout();

    int getCounter();
}
