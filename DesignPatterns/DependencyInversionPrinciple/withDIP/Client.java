import javax.xml.crypto.Data;

class Client {
    public static void main(String[] args) {

        System.out.println("****A Demo that follows the DIP***");

        // Using the Oracle DB
        Database database = new OracleDatabase();
        UserInterface userInterface = new UserInterface(database);
        userInterface.saveEmpId("E0001");

        // Using the MYSQL DB
        database = new MySqlDatabase();
        userInterface = new UserInterface(database);
        userInterface.saveEmpId("E0002");

    }
}

class UserInterface {

    Database database;

    UserInterface(Database database) {
        this.database = database;
    }

    void saveEmpId(String empId) {
        this.database.saveEmpIdInDB(empId);
    }
}

interface Database {

    void saveEmpIdInDB(String empId);

}

class OracleDatabase implements Database {

    @Override
    public void saveEmpIdInDB(String empId) {
        System.out.println("Saving the empId: " + empId + " to the Oracle DB");
    }

}

class MySqlDatabase implements Database {

    @Override
    public void saveEmpIdInDB(String empId) {

        System.out.println("Saving the empId: " + empId + " to the MySql DB");
    }

}