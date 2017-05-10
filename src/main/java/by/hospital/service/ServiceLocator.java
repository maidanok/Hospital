package by.hospital.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 06.05.2017.
 */
public class ServiceLocator {

    private static ServiceLocator instance;

    private Map<Class<?>, Object> services = new HashMap<Class<?>, Object>();

    public void registerService(Class<?> serviceClass, Object service) {
        services.put(serviceClass, service);
    }

    public static void setLocator(ServiceLocator locator) {
        instance = locator;
    }

    @SuppressWarnings("unchecked")
    public static <Type> Type getService(Class<Type> serviceClass) {
        return (Type) instance.services.get(serviceClass);
    }

}
