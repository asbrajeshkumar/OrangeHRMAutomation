package framework.extentReport;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestReporter {
	public static void log(Status status, String message) {
    	ExtentTest extentTest = ExtentManager.getExtentTest();
        if (extentTest != null) {
        	extentTest.log(status, message);
        } else {
            System.err.println("[ExtentTest NOT initialized] " + Status.FAIL + ": Failed to log message in reporter.");
        }
    }

    public static void pass(String message) {
        log(Status.PASS, message);
    }

    public static void fail(String message) {
        log(Status.FAIL, message);
    }

    public static void info(String message) {
    	log(Status.INFO, message);
    }

    public static void skip(String message) {
    	log(Status.SKIP, message);
    }

    

    
    
    
    
    
}
