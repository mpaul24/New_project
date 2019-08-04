package DependencyInjection.dependencyInjector;

import DependencyInjection.application.Application;

public interface Injector {
    Application getServer(String serviceName);
}
