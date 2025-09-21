package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	public static Properties props;
	
	public ConfigManager() {
		
	}
	
	static void loadProperties() {
		if(null==props) {
			try {
				FileReader reader = new FileReader(Constants.RUN_CONFIG);
				props = new Properties();
				props.load(reader);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static {
		loadProperties();
	}

	public static Properties getProps() {
		return props;
	}
	
	public static void setProps(Properties props) {
		ConfigManager.props = props;
	}
    
}
