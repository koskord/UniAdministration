package mainpackage;


import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Students extends Users implements Serializable{
	
        //Attributes
        private String studentID;
        private Vector v = new Vector();

        //Constructors
	public Students(String username, String password, String surname,String name, String id){
            super(surname, name,username,password);
	}
        
        public Students(String id){
            this.studentID=id;
            v = this.getStudentByID(id);
            if(v.size()!=0){
                this.setSurname(v.elementAt(0).toString());
                this.setName(v.elementAt(1).toString());
                this.setUsername(v.elementAt(2).toString());
                this.setPassword(v.elementAt(3).toString());            
            }
 
	}
        
        public Students(){
	}

        //Methods
        
        public boolean createTeamPerCourse(String courseCode,Vector studentList){
            
        boolean teamCreated = false;
        this.connect();
            try {
                    int primaryKey = 0;
                    String sql = "INSERT INTO teams(id_courses) VALUES(?)";
                                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                ps.setString(1, courseCode);
                                ps.executeUpdate();  
                                ResultSet generatedKeys = ps.getGeneratedKeys();
                                if (null != generatedKeys && generatedKeys.next()) {
                                     primaryKey = generatedKeys.getInt(1);
                                     System.out.println(primaryKey);
                                }

                            for (int i = 0; i < studentList.size(); i++) {
                                String sql1 = "INSERT INTO student_list(id_teams,id_students) VALUES(?,?)";
                                PreparedStatement ps1 = con.prepareStatement(sql1);
                                ps1.setInt(1, primaryKey);
                                ps1.setString(2, String.valueOf(studentList.elementAt(i)));
                                ps1.executeUpdate();
                            }
                        teamCreated = true; 

            } catch (SQLException ex) {
                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
                teamCreated=false;
            }
            this.disconnect();  
           
            return teamCreated;
            
        }//ok

        public void updateTeamPerCourse(Courses course,Vector studentsGroup){

        
        System.out.println("\nTeam updated!!");

    }//Den leitourgei akoma
        
        public Vector viewTeamPerCourse(String studentID){
                    Vector v ; 
                    Vector vFinal = new Vector();
                    connect();
                    try {
                            String sql ="SELECT T.id_teams, C.id_course, C.course_name\n" +
                                        "FROM courses C, teams T, student_list S, students ST WHERE\n" +
                                        "T.id_teams=S.id_teams AND\n" +
                                        "C.id_course=T.id_courses AND\n" +
                                        "ST.id_students=S.id_students AND\n" +
                                        "S.id_students='"+studentID+"'";
                            Statement s = con.createStatement();
                            ResultSet r = s.executeQuery(sql);
                            ResultSetMetaData rsmd = r.getMetaData();
                            while(r.next()){
                                v = new Vector();
                                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                                    v.addElement(r.getString(i));
                                }
                                vFinal.addElement(v);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    disconnect();
                    return vFinal;
                    
                }//ok

        public Vector viewAvailableCourses(){
                        connect();
                        Vector str = new Vector();
                        try {
                            String sql = "SELECT * FROM `courses` ";
                            Statement s = con.createStatement();
                            ResultSet r = s.executeQuery(sql);
                            while(r.next()){
                                Courses c = new Courses(r.getString(1));
                                str.addElement(c);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        disconnect();
                        
                return str;
        }//ok
        
        //Getters - Setters
        
        public String getStudentID() {
            return studentID;
        }

        public void setStudentID(String studentID) {
            this.studentID = studentID;
        }

}
