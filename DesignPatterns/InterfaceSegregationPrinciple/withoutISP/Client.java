class Client {
    public static void main(String[] args) {

        System.out.println("*******Without ISP****");

        Printer printer = new AdvancedPrinter();
        printer.printDocument();
        printer.sendFax();

        printer = new BasicPrinter();
        printer.printDocument();
        //printer.sendFax();
        
    }
}
interface Printer {
    void printDocument();
    void sendFax();
}

class BasicPrinter implements Printer {

    @Override
    public void printDocument() {
        
        System.out.println("Printing the document from the basic printer");
    }

    @Override
    public void sendFax() {
        
        throw new UnsupportedOperationException("Unimplemented method 'sendFax'");
    }

}

class AdvancedPrinter implements Printer {

    @Override
    public void printDocument() {
        System.out.println("Printing the document from Advanced Printer");
    }

    @Override
    public void sendFax() {
       System.out.println("Sending the fax from the advanced printer");
    }

}