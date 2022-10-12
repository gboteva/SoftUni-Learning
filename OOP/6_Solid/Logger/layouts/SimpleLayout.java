package layouts;

import loggers.ReportLevel;

public class SimpleLayout implements Layout{


    public SimpleLayout(){
    }

    @Override
    public String formatMessage(String timeStamp, ReportLevel reportLevel, String message) {
        return String.format("%s - %s - %s", timeStamp, reportLevel, message);
    }
}
