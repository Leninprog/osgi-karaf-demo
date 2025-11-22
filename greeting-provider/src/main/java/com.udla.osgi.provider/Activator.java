package com.udla.osgi.provider;

import com.udla.osgi.api.GreetingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration<GreetingService> registration;

    @Override
    public void start(BundleContext context) {
        GreetingService service = new GreetingServiceImpl();
        registration = context.registerService(GreetingService.class, service, null);
        System.out.println("[Provider] GreetingService publicado");
    }

    @Override
    public void stop(BundleContext context) {
        if (registration != null) {
            registration.unregister();
        }

        System.out.println("[Provider] GreetingService retirado");
    }
}
