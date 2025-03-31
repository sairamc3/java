import java.util.ArrayList;
import java.util.List;
class Client {

    public static void main(String[] args) {
        System.out.println(
            "*****A demo without LSP*****"
        );

        PaymentHelper paymentHelper = new PaymentHelper();

        RegisteredPayment robinPayment = new RegisteredPayment("Robin");
        RegisteredPayment jackPayment = new RegisteredPayment("Jack");

        paymentHelper.addUser(robinPayment);
        paymentHelper.addUser(jackPayment);

        // Guest User
        GuestPayment guestuser = new GuestPayment("Dale");
        paymentHelper.addUser(guestuser);

        paymentHelper.showPreviousPayments();
        paymentHelper.processNewPayments();


    }
}

interface Payment {
    void previousPaymentInfo();
    void newPayment();
}

class RegisteredPayment implements Payment {

    String name;

    public RegisteredPayment(String username){
        this.name = username;
    }

    @Override
    public void previousPaymentInfo(){
        System.out.println("Retrieving " + name + "'s last payment details.");
    }

    @Override
    public void newPayment(){
        System.out.println("Processing " + name + "'s current Payment request");
    }
}

class GuestPayment implements Payment {

    String name;

    public GuestPayment(String username){
        this.name = username;
    }

    @Override
    public void previousPaymentInfo(){
        throw new UnsupportedOperationException("Previous payment Information not implemented for Guest user");
    }

    @Override
    public void newPayment(){
        System.out.println("Processing " + name + "'s current Payment request");
    }
}

class PaymentHelper {

    List<Payment> payments = new ArrayList<>();

    public void addUser(Payment user){
        payments.add(user);
    }

    public void showPreviousPayments(){
        for(Payment payment: payments){
            payment.previousPaymentInfo();
            System.out.println("----------");
        }
    }

    public void processNewPayments(){
        for(Payment payment: payments){
            payment.newPayment();
            System.out.println("-----------");
        }
    }
}