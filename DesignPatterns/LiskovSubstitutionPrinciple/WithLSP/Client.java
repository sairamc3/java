package WithLSP;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {

        System.out.println("*** A Demo that follows the LSP. ***\n");

        PaymentHelper paymentHelper = new PaymentHelper();

        RegisteredPayment robinPayment = new RegisteredPayment("Robin");
        RegisteredPayment jackPayment = new RegisteredPayment("Jack");

        GuestPayment guestUser = new GuestPayment("Guest1");

        // Add previous payments for Registerd Users
        paymentHelper.addPreviousPayment(jackPayment);
        paymentHelper.addPreviousPayment(robinPayment);

        // Current Payment for both registered and guest users
        paymentHelper.addNewPayment(jackPayment);
        paymentHelper.addNewPayment(robinPayment);
        paymentHelper.addNewPayment(guestUser);

        paymentHelper.showPreviousPayments();
        paymentHelper.processNewPayments();

    }

}

interface PreviousPayment {
    void previousPaymentInfo();
}

interface NewPayment {
    void newPayment();
}

class RegisteredPayment implements NewPayment, PreviousPayment {

    String name;

    RegisteredPayment(String name) {
        this.name = name;
    }

    @Override
    public void newPayment() {

        System.out.println("Processing " + name + "'s current Payment request");
    }

    @Override
    public void previousPaymentInfo() {
        System.out.println("Retrieving " + name + "'s last payment details");

    }
}

class GuestPayment implements NewPayment {

    String name;

    GuestPayment(String name) {
        this.name = name;
    }

    @Override
    public void newPayment() {
        System.out.println("Processing " + name + "'s current Payment request");
    }
}

class PaymentHelper {

    List<PreviousPayment> previousPayments = new ArrayList<>();

    List<NewPayment> newPayments = new ArrayList<>();

    public void addPreviousPayment(PreviousPayment previousPayment) {
        previousPayments.add(previousPayment);
    }

    public void addNewPayment(NewPayment newPayment) {
        newPayments.add(newPayment);
    }

    public void showPreviousPayments() {
        for (PreviousPayment previousPayment : previousPayments) {
            previousPayment.previousPaymentInfo();
            System.out.println("---------");
        }
    }

    public void processNewPayments() {
        for (NewPayment newPayment : newPayments) {
            newPayment.newPayment();
            System.out.println("********");
        }
    }
}