package mainpackage;

import java.util.Scanner;
import java.util.Vector;


public class Admins extends Users {

    //Attributes
    String localUsername, localSurname, localName, localPassword, id, localProfCode;
    Scanner input = new Scanner(System.in);

    //Constructors
    public Admins(String surname, String name, String username, String password) {
        super(surname, name, username, password);
    }

    public Admins() {

    }

    //Methods
    public void createUser(int userSelection) {

    }//Dhmiourgei eggrafh sto Admins, Users h Professors

    public Courses createCourse() {
        Courses c = new Courses();
        return c;
    }//Dhmiourgei ena mathima

    public void deleteUser(Object a) {

        System.out.println("User deleted");
    }//Proswrina epistrefei mhnyma

    public void updateUser(Object a) {

        System.out.println("User updated");
    }//Proswrina epistrefei mhnyma

    public Vector viewAllUsers() {
        Vector allUsers = new Vector();
        allUsers.addElement("This is the list of all Users");

        return allUsers;
    }//Proswrina epistrefei mhnyma

    public Vector viewAvailableCourses() {
        Vector v = new Vector();

        return v;
    }//emfanizei ta diathesima mathimata ston mathiti

    //Setters - Getters
    public String getLocalUsername() {
        return localUsername;
    }

    public void setLocalUsername(String localUsername) {
        this.localUsername = localUsername;
    }

    public String getLocalSurname() {
        return localSurname;
    }

    public void setLocalSurname(String localSurname) {
        this.localSurname = localSurname;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getLocalPassword() {
        return localPassword;
    }

    public void setLocalPassword(String localPassword) {
        this.localPassword = localPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
