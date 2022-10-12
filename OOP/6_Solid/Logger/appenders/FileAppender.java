package appenders;

import layouts.Layout;
import loggers.ReportLevel;
import utilities.File;

public class FileAppender extends BaseAppender{
   private File file;


    public FileAppender(Layout layout, File file) {
        super(layout);
        this.file = file;
    }

    @Override
    public void append(String timeStamp, ReportLevel reportLevel, String message) {
        counter++;
        file.write(getLayout().formatMessage(timeStamp, reportLevel, message));
    }

    public File getFile() {
        return file;
    }
}
