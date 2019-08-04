public class MessageService {
    void printEmail(String receiver, String message) {
        System.out.println("Email sent to: " + receiver + " message:" + message);
    }

    void printSms(String receiver, String message) {
        System.out.println("Sms sent to: " + receiver + " message:" + message);
    }

    void printFb(String receiver, String message) {
        System.out.println("Facebook sent to: " + receiver + " message:" + message);
    }
}
