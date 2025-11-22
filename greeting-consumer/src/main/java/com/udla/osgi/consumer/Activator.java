package com.udla.osgi.consumer;

import com.udla.osgi.api.GreetingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    private ServiceTracker<GreetingService, GreetingService> tracker;

    private volatile boolean running = true;

    @Override
    public void start(BundleContext context) {
        tracker = new ServiceTracker<>(context, GreetingService.class, null);
        tracker.open();
        System.out.println("[Consumer] Buscando GreetingService...");

        new Thread(() -> {
            while (running) {
                GreetingService service = tracker.getService();
                if (service != null) {
                    System.out.println("[Consumer] " + service.greet("Nicolas"));
                } else {
                    System.out.println("[Consumer] Servicio no disponible (provider apagado)");
                }
                try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
            }
        }).start();
    }

    @Override
    public void stop(BundleContext context) {
        running = false;
        tracker.close();
        System.out.println("[Consumer] Bundle detenido");
    }
}
