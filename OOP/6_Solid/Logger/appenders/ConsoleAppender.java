package appenders;

import layouts.Layout;
import loggers.ReportLevel;

public class ConsoleAppender extends BaseAppender{

    public ConsoleAppender (Layout layout){
        super(layout);
    }

    @Override
    public void append(String timeStamp, ReportLevel reportLevel, String message) {
        counter++;
        System.out.println(getLayout().formatMessage(timeStamp, reportLevel, message));
    }
}
