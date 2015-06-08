package mainpackage;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

	public class Professors extends Users {
                
            
                //Attributes
		private Vector v = new Vector();
                //Constructors
                public Professors (int id){
			v = this.getProfessorByID(id);
                        if(v.size()!=0){
                            this.setSurname(v.elementAt(1).toString());
                            this.setName(v.elementAt(2).toString());
                            this.setUsername(v.elementAt(3).toString());
                            this.setPassword(v.elementAt(4).toString());
                        }
                        
		}
                
                public Professors (){
		}
                
                //Methods
                public Vector viewTeamPerCourse(String teamID){
                    Vector v ; 
                    Vector vFinal = new Vector();
                    connect();
                    try {
                            String sql ="SELECT ST.id_students,ST.stud_surname,ST.stud_name\n" +
                                        "FROM student_list S, students ST WHERE\n" +
                                        "S.id_students=ST.id_students AND\n" +
                                        "S.id_teams='"+teamID+"'";
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
                
                public Vector viewProfessorCourses(String profID){
                    Vector vFinal = new Vector();
                    connect();
                    try {
                            String sql ="SELECT  C.id_course\n" +
                                        "FROM courses C WHERE\n" +
                                        "C.id_professors='"+profID+"'";
                            Statement s = con.createStatement();
                            ResultSet r = s.executeQuery(sql);
                            while(r.next()){
                                Courses c = new Courses(r.getString(1));
                                vFinal.addElement(c);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    disconnect();
                    return vFinal;
                }//ok

                public Vector getTeamPerCourse(String courseID){
                    Vector vFinal = new Vector();
                    connect();
                    try {
                            String sql ="SELECT  T.id_teams\n" +
                                        "FROM teams T WHERE\n" +
                                        "T.id_courses='"+courseID+"'";
                            Statement s = con.createStatement();
                            ResultSet r = s.executeQuery(sql);
                            while(r.next()){
                                vFinal.addElement(r.getString(1));
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    disconnect();
                    return vFinal;
                }//ok
                
}
