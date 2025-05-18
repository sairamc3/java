class Client {
    public static void main(String[] args) {
        System.out.println("*** Without Dependency Inversion Principle****");

        UserInterface userInterface = new UserInterface();
        userInterface.saveEmpId("E001");

    }
}

class UserInterface {
    private OracleDatabase oracleDatabase;

    public UserInterface() {
        this.oracleDatabase = new OracleDatabase();
    }

    public void saveEmpId(String empId) {
        this.oracleDatabase.saveEmpIdInDB(empId);
    }
}

class OracleDatabase {

    public void saveEmpIdInDB(String empId) {
        System.out.println("The id: " + empId + " has been saved to Oracle Database");
    }

}