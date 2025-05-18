class Client {
    public static void main(String[] args) {

        System.out.println("**** With ISP *****");

        Printer printer = new AdvancedPrinter();
        printer.printDocument();

        printer = new BasicPrinter();
        printer.printDocument();

        FaxDevice fax = new AdvancedPrinter();
        fax.sendFax();

    }
}

interface Printer {

    void printDocument();
}

interface FaxDevice {

    void sendFax();
}

class BasicPrinter implements Printer {

    @Override
    public void printDocument() {
        System.out.println("Printing the document from the basic printer");
    }

}

class AdvancedPrinter implements Printer, FaxDevice {

    @Override
    public void sendFax() {
        System.out.println("Sending fax from the Advanced Printer");
    }

    @Override
    public void printDocument() {
        System.out.println("Printing the document from the Advanced Printer");
    }

}
