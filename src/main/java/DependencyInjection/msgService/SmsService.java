package DependencyInjection.msgService;

public class SmsService implements MessageService {
    @Override
    public void sendMessage(String receiver, String message) {
        System.out.println("Sms Message: " + message + " sent to: " + receiver);
    }
}
