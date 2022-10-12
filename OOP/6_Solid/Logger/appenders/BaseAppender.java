package appenders;

import layouts.Layout;
import loggers.ReportLevel;

public abstract class BaseAppender implements Appender{
    private Layout layout;
    protected int counter;

    public BaseAppender(Layout layout) {
        this.layout = layout;
    }

   @Override
    public Layout getLayout() {
        return layout;
    }

    public int getCounter(){
        return this.counter;
    }





}
