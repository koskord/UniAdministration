package mainpackage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Users {

    //Attributes
    private String name;
    private String surname;
    private static String username;
    private String password;
    private String code;

    public Connection con;

    //Constructors
    public Users(String surname, String name, String username, String password, String code) {
        Users.username = username;
        this.surname = surname;
        this.name = name;
        this.password = password;
        this.code = code;
    }

    public Users(String surname, String name, String username, String password) {
        Users.username = username;
        this.surname = surname;
        this.name = name;
        this.password = password;
    }

    public Users() {

    }

        //Methods
    public boolean checkIfCourseExist(String courseCode) {
        this.connect();
        boolean temp = false;
        try {
            String sql = "SELECT id_course FROM courses";
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                if (r.getString(1).equalsIgnoreCase(courseCode)) {
                    temp = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.disconnect();
        return temp;

    }//ok

    public boolean checkIfStudentsExist(Vector studentsListID) {
        this.connect();
        boolean ok = false;
        try {
            for (int i = 0; i < studentsListID.size(); i++) {
                String sql = "SELECT * FROM students WHERE id_students='" + studentsListID.elementAt(i) + "'";
                Statement s = con.createStatement();
                ResultSet r = s.executeQuery(sql);
                r.next();
                if (r.getRow() >= 1) {
                    ok = true;
                } else {
                    ok = false;
                    break;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.disconnect();
        return ok;

    }//ok

    public boolean checkIfProfessorExist(String professorCode) {
        this.connect();
        boolean ok = false;
        try {
            String sql = "SELECT * FROM professors WHERE id_professors='" + professorCode + "'";
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(sql);
            r.next();
            if (r.getRow() >= 1) {
                ok = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.disconnect();
        return ok;

    }//ok

    public boolean checkIfStudentHasTeam(String studentId, String courseId) {
        this.connect();
        boolean ok = false;
        try {
            String sql = "SELECT T.id_courses FROM student_list S, students ST,teams T WHERE\n"
                    + "S.id_teams=T.id_teams AND\n"
                    + "S.id_teams=T.id_teams AND\n"
                    + "ST.id_students=S.id_students AND\n"
                    + "ST.id_students='" + studentId + "'";
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {

                if (r.getRow() < 1) {
                    ok = false;
                } else {
                    System.out.println(r.getString(1));

                    if (r.getString(1).equals(courseId)) {
                        ok = true;
                    } else {
                        ok = false;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.disconnect();
        return ok;

    }//ok

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");//fortwsi tou driver mysql (prepei na fortwsw kai tin library tis mysql..)
            con = DriverManager.getConnection("jdbc:mysql://localhost/papeijava", "root", "1234");
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//ok

    public void disconnect() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//ok

    public Vector getStudentByID(String id) {
        connect();
        Vector str = new Vector();
        try {
            String sql = "SELECT stud_surname,stud_name,stud_username,stud_password FROM students WHERE id_students= '" + id + "'";
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(sql);
            r.next();
            if (r.getRow() < 1) {
                System.out.println("den yparxei o student");
            } else {
                str.addElement(r.getString(1));
                str.addElement(r.getString(2));
                str.addElement(r.getString(3));
                str.addElement(r.getString(4));
            }

                        //}
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        return str;
    }//ok

    public Vector getProfessorByID(int id) {
        connect();
        Vector str = new Vector();
        try {
            String sql = "SELECT * FROM `professors` WHERE `id_professors`= " + id;
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(sql);
            r.next();
            str.addElement(r.getString(1));
            str.addElement(r.getString(2));
            str.addElement(r.getString(3));
            str.addElement(r.getString(4));
            str.addElement(r.getString(5));

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        return str;
    }//ok

    public int loginUsers(String uname, String pass) {
        int ok = 0;
        this.connect();
        try {
            String sql = "SELECT S.stud_username, S.stud_password\n"
                    + "FROM students S\n"
                    + "WHERE S.stud_username ='" + uname + "'\n"
                    + "AND S.stud_password ='" + pass + "'";
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(sql);
            r.next();
            if (r.getRow() < 1) {
                ok = 0;
            } else {
                ok = 1;
            }
            if (ok != 1) {
                String sql1 = "SELECT P.prof_username, P.prof_password\n"
                        + "FROM professors P\n"
                        + "WHERE P.prof_username ='" + uname + "'\n"
                        + "AND P.prof_password ='" + pass + "'";
                Statement s1 = con.createStatement();
                ResultSet r1 = s1.executeQuery(sql1);
                r1.next();

                if (r1.getRow() < 1) {
                    ok = 0;
                } else {
                    ok = 2;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.disconnect();
        return ok;
    }//ok 
    //αν είναι μαθητής επιστρέφει 1 , αν είναι καθηγητής επιστρέφει 2

    public String returnUserID(String uname, String pass) {
        int ok = 0;
        String userID = "";
        this.connect();
        try {
            String sql = "SELECT S.id_students,S.stud_username, S.stud_password\n"
                    + "FROM students S\n"
                    + "WHERE S.stud_username ='" + uname + "'\n"
                    + "AND S.stud_password ='" + pass + "'";
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(sql);
            r.next();
            if (r.getRow() < 1) {
                ok = 0;
            } else {
                ok = 1;
                userID = r.getString(1);
            }
            if (ok != 1) {
                String sql1 = "SELECT P.id_professors, P.prof_username, P.prof_password\n"
                        + "FROM professors P\n"
                        + "WHERE P.prof_username ='" + uname + "'\n"
                        + "AND P.prof_password ='" + pass + "'";
                Statement s1 = con.createStatement();
                ResultSet r1 = s1.executeQuery(sql1);
                r1.next();

                if (r1.getRow() < 1) {
                    ok = 0;
                } else {
                    ok = 2;
                    userID = r1.getString(1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.disconnect();
        return userID;
    }//ok 
    // epistrefei to id tou user

    //Getters-Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Users.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
