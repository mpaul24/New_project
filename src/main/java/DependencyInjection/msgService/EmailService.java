package DependencyInjection.msgService;

public class EmailService implements MessageService {
    @Override
    public void sendMessage(String receiver, String message) {
        System.out.println("Email Message: " + message + " sent to: " + receiver);
    }
}
