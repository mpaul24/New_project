public class Message {
    /**
     * @param serviceType
     * email, sms, facebook
     * @return
     */
    int compare(String serviceType){
        if(serviceType.equals("email")){
            return 1;
        }else if(serviceType.equals("sms")){
            return 2;
        }else{
            return 3;
        }
    }

    void send(int k){
        if(k==1){
            MessageService obj = new MessageService();
            //obj.printEmail(MainClass.receiver, MainClass.message);
        }else if(k==2){
            MessageService obj = new MessageService();
            //obj.printSms(MainClass.receiver, MainClass.message);
        }else{
            MessageService obj = new MessageService();
            //obj.printFb(MainClass.receiver, MainClass.message);
        }
    }
}
