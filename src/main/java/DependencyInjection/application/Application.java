package DependencyInjection.application;

import DependencyInjection.msgService.MessageService;

public class Application implements ApplicationService{
    private MessageService mMessageService;

    public Application(MessageService messageService) {
        this.mMessageService = messageService;
    }

    @Override
    public void sendMessage(String receiver, String message) {
        mMessageService.sendMessage(receiver,message);
    }
}
