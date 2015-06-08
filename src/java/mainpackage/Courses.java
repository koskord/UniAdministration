package mainpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Courses {

    
    //Attributes 
    private String courseId;
    private String courseName;
    private Professors courseProfessor;
    private String courseSemester;
    private String courseDescription;
    private Vector courseVector;
    private Vector profVector;
    public Connection con;

    //Constructors
    public Courses(String id) {

        courseVector = this.getCourseByID(id);
        if (!courseVector.isEmpty()) {
            this.courseId = courseVector.elementAt(0).toString();
            this.courseName = courseVector.elementAt(1).toString();
            this.courseDescription = courseVector.elementAt(2).toString();
            this.courseSemester = courseVector.elementAt(3).toString();
            this.courseProfessor = this.getProfessorByID(courseVector.elementAt(4).toString());
        }

    }

    public Courses(String id, String name, String semester, String description, Professors professor) {
        courseVector = this.getCourseByID(id);
        this.courseId = courseVector.elementAt(0).toString();
        this.courseName = courseVector.elementAt(1).toString();
        this.courseDescription = courseVector.elementAt(2).toString();
        this.courseSemester = courseVector.elementAt(3).toString();
        this.courseProfessor = this.getProfessorByID(courseVector.elementAt(4).toString());
    }

    public Courses() {
        this.courseId = null;
    }

    //Setters - Getters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Professors getCourseProfessor() {
        return courseProfessor;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseProfessor(Professors courseProfessor) {
        this.courseProfessor = courseProfessor;
    }

    public String getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(String courseSemester) {
        this.courseSemester = courseSemester;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Vector getCourseByID(String id) {

        connect();
        Vector str = new Vector();
        if (this.checkIfCourseExist(id)) {
            try {
                String sql = "SELECT * FROM `courses` WHERE `id_course`= '" + id + "'";
                Statement s = con.createStatement();
                ResultSet r = s.executeQuery(sql);
                r.next();
                str.addElement(r.getString(1));
                str.addElement(r.getString(2));
                str.addElement(r.getString(3));
                str.addElement(r.getString(4));
                str.addElement(r.getString(5));

            } catch (SQLException ex) {
                Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, ex);
            }
            disconnect();
        }
        return str;
    }//ok

    public Professors getProfessorByID(String id) {
        connect();
        Professors str = new Professors();
        try {
            String sql = "SELECT * FROM `professors` WHERE `id_professors`= " + id;
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(sql);
            r.next();
            str.setCode(r.getString(1));
            str.setSurname(r.getString(2));
            str.setName(r.getString(3));
            str.setUsername(r.getString(4));
            str.setPassword(r.getString(4));

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        return str;
    }//ok

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");//fortwsi tou driver mysql (prepei na fortwsw kai tin library tis mysql..)
            con = DriverManager.getConnection("jdbc:mysql://localhost/papeijava", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkIfCourseExist(String courseCode) {
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

        return temp;

    }//ok

}
