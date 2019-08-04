package DependencyInjection.dependencyInjector;

import DependencyInjection.application.Application;
import DependencyInjection.msgService.MessageServiceProvider;

public class MessageServiceInjector implements Injector {
    @Override
    public Application getServer(String serviceName) {
        Application application =
                new Application(MessageServiceProvider.getMessageService(serviceName));
        return application;
    }
}
