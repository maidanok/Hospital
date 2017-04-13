package by.hospital.prop_managers;

import java.util.ResourceBundle;

/**
 * Created by Pasha on 10.04.2017.
 */
public class ConnectionManager {
    private final static ResourceBundle RESOURCE_BUNDLE=ResourceBundle.getBundle("properties.connection");

    public static String getProperty(String key){
        return RESOURCE_BUNDLE.getString(key);
    }
}
