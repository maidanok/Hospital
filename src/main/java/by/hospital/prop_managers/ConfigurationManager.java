package by.hospital.prop_managers;

import java.util.ResourceBundle;

/**
 * Created by Admin on 07.05.2017.
 */
public class ConfigurationManager {
    private final static ResourceBundle RESOURCE_BUNDLE=ResourceBundle.getBundle("properties.config");

    public static String getProperty(String key){
        return RESOURCE_BUNDLE.getString(key);
    }
}
