package DependencyInjection.msgService;

public class MessageServiceProvider {

    public static MessageService getMessageService(String service) {
        MessageService messageService;
        switch (service) {
            case "Email":
                messageService = new EmailService();
                break;
            case "Sms":
                messageService = new SmsService();
                break;
            default:
                throw new IllegalArgumentException();
        }
        return messageService;
    }
}
