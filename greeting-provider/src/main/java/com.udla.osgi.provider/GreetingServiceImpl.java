package com.udla.osgi.provider;

import com.udla.osgi.api.GreetingService;

public class GreetingServiceImpl implements GreetingService {

    @Override
    public String greet(String name) {
        return "Hola " + name + " desde OSGi/Karaf!";

    }
}
