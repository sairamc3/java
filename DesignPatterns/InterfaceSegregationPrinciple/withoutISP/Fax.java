interface Fax {
    void faxType();
}

class LanFax implements Fax {

    @Override
    public void faxType(){
        System.out.println("Using LAN fax to send the fax.");
    }
}

class Efax implements Fax {

    @Override
    public void faxType(){
        System.out.println("Using internet fax(eFax) to send the fax");
    }
}