package utility;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class LogUtil {
	public static Logger getLogger(Class<?> cls) {
		String sDate = Constants.sInitDate;
		String sTime = Constants.sInitTime;
		
        String LOGPATH = Constants.LOG_PATH + File.separator + File.separator+sDate+"_"+sTime+".log";
        System.setProperty("LOGPATH", LOGPATH);
		PropertyConfigurator.configure(Constants.LOG_CONFIG);
        return Logger.getLogger(cls);
    }
}
