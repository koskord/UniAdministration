package mainpackage;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mainpackage.Courses;
import mainpackage.Professors;
import mainpackage.Students;
import mainpackage.Users;




@WebServlet(urlPatterns = {"/testServlet"})
public class testServlet extends HttpServlet {

    public String userID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int hiddenParam = Integer.parseInt(request.getParameter("function"));
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //this.getStudentNames(StudentId);
        switch (hiddenParam) {
            case 1:// Dhmiourgia Omadas
                String CourseId = request.getParameter("idCourse");
                Courses selectedCourse = new Courses(CourseId);
                Vector StudentsIDs = new Vector();
                StudentsIDs.add(request.getParameter("idStudent1"));
                Students s1 = new Students(StudentsIDs.elementAt(0).toString());
                StudentsIDs.add(request.getParameter("idStudent2"));
                Students s2 = new Students(StudentsIDs.elementAt(1).toString());
                StudentsIDs.add(request.getParameter("idStudent3"));
                Students s3 = new Students(StudentsIDs.elementAt(2).toString());
                Students s5 = null;
                Students s4 = null;
                boolean ok1;
                boolean ok2;
                if (request.getParameter("idStudent4") != "") {
                    StudentsIDs.add(request.getParameter("idStudent4"));
                    s4 = new Students(StudentsIDs.elementAt(3).toString());
                }
                if (request.getParameter("idStudent5") != "") {
                    StudentsIDs.add(request.getParameter("idStudent5"));
                    s5 = new Students(StudentsIDs.elementAt(4).toString());
                }

                Students s = new Students(userID);

                try {
                    printHeader("Team Creation", out);
                    if (s1.checkIfStudentsExist(StudentsIDs)) {
                        if (s.checkIfCourseExist(CourseId)) {
                            if (!s1.checkIfStudentHasTeam(s1.getStudentID(), CourseId)
                                    && !s2.checkIfStudentHasTeam(s2.getStudentID(), CourseId)
                                    && !s3.checkIfStudentHasTeam(s3.getStudentID(), CourseId)) {
                                if (s.createTeamPerCourse(CourseId, StudentsIDs)) {
                                    out.println("<h1>Η ομάδα σας δημιουργήθηκε με επιτυχία</h1>");
                                    out.println("<U>Στοιχεία Καταχώρησης</U><br><br>");
                                    out.println("Μάθημα : " + selectedCourse.getCourseName() + " (ID: " + selectedCourse.getCourseId() + ")<br><br>");
                                    out.println("Διδάσκων : " + selectedCourse.getCourseProfessor().getSurname() + " " + selectedCourse.getCourseProfessor().getName() + "<br><br>");
                                    out.println("<U>Ομάδα Εργασίας</U><br>");
                                    out.println("<ol>");
                                    out.println("<li>" + s1.getSurname() + " " + s1.getName() + "</li>");
                                    out.println("<li>" + s2.getSurname() + " " + s2.getName() + "</li>");
                                    out.println("<li>" + s3.getSurname() + " " + s3.getName() + "</li>");
                                    if (request.getParameter("idStudent4") != "") {
                                        out.println("<li>" + s4.getSurname() + " " + s4.getName() + "</li>");
                                    }
                                    if (request.getParameter("idStudent5") != "") {
                                        out.println("<li>" + s5.getSurname() + " " + s5.getName() + "</li>");
                                    }
                                    out.println("</ol>");
                                } else {
                                    out.println("<h1>Η δημιουργία της Ομάδας Απέτυχε!!</h1>");
                                    out.println("<h4>Άγνωστο Σφάλμα !!!</h4>");
                                }

                            } else {
                                out.println("<h1>Η δημιουργία της Ομάδας Απέτυχε!!</h1>");
                                out.println("<h4>Πιθανό Σφάλμα :</h4>");
                                out.println("<ul>"
                                        + "<li>Κάποιος μαθητής ανηκει σε καποια αλλη ομάδα.</li>"
                                        + "</ul>");
                            }
                        } else {
                            out.println("<h1>Η δημιουργία της Ομάδας Απέτυχε!!</h1>");
                            out.println("<h4>Πιθανό Σφάλμα :</h4>");
                            out.println("<ul>"
                                    + "<li>Ο κωδικός μαθήματος δεν υπάρχει.</li>"
                                    + "</ul>");
                        }
                    } else {
                        out.println("<h1>Η δημιουργία της Ομάδας Απέτυχε!!</h1>");
                        out.println("<h4>Πιθανό Σφάλμα :</h4>");
                        out.println("<ul>"
                                + "<li>Ο κωδικός κάποιων μαθητών δεν υπάρχει.</li>"
                                + "</ul>");
                    }
                    out.println("<br><button class=\"button\" onclick=\"window.history.back();\">Go Back</button>&nbsp;&nbsp;<input class=\"reset\" type=\"button\" value=\"Close\" onclick=\"window.close();\" />");
                    out.println("</body>");
                    out.println("</html>");
                } finally {
                    out.close();
                }
                break;
            case 2://Εμφανιση διαθεσιμων μαθημάτων

                Students me = new Students();
                Vector alltheCoursesObjects = new Vector();
                alltheCoursesObjects = me.viewAvailableCourses();
                try {
                    printHeader("Available Courses", out);
                    out.println("<center><h1>Available Courses</h1><br><br>");
                    out.println("<table border=\"2\">");
                    out.println("<thead>\n"
                            + "  <tr>\n"
                            + "     <th>A/A</th>\n"
                            + "     <th>Course Name</th>\n"
                            + "     <th>Course Code</th>\n"
                            + "  </tr>\n"
                            + " </thead>");
                    for (int i = 0; i < alltheCoursesObjects.size(); i++) {
                        out.println("<tr>");
                        Courses c = new Courses();
                        c = (Courses) alltheCoursesObjects.elementAt(i);
                        out.println("<td>" + (i + 1) + ". </td><td>" + c.getCourseName() + "</td><td>" + c.getCourseId() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<br><button class=\"reset\" onclick=\"window.history.back();\">Go Back</button>");
                    out.println("</center></body>");
                    out.println("</html>");
                } finally {
                    out.close();
                }

                break;
            case 3://Εμφανιση των ομάδων μου
                //String myId = request.getParameter("id");
                Students me3 = new Students(userID);
                Vector studentID = new Vector();
                studentID.addElement(userID);
                Vector incomingVectorWithResulSet = new Vector();
                incomingVectorWithResulSet = me3.viewTeamPerCourse(userID);
                if (me3.checkIfStudentsExist(studentID)) {
                    Vector vectorWithRows = new Vector();
                    try {
                        printHeader("Student Details", out);
                        out.println("<center><h2><u>Student Details</u></h2>");
                        out.println("<h4>" + me3.getSurname() + " " + me3.getName() + " (AM : " + me3.getStudentID() + ")</h4>");
                        out.println("<h2><u>Student Teams</u></h2><br>");
                        out.println("<table border=\"2\">");
                        out.println("<thead>\n"
                                + "  <tr>\n"
                                + "     <th>A/A</th>\n"
                                + "     <th>Team Code</th>\n"
                                + "     <th>Course Code</th>\n"
                                + "     <th>Course Name</th>\n"
                                + "  </tr>\n"
                                + " </thead>");
                        for (int i = 0; i < incomingVectorWithResulSet.size(); i++) {
                            vectorWithRows = (Vector) incomingVectorWithResulSet.elementAt(i);
                            out.println("<tr>");
                            out.println("<td>" + (i + 1) + ". </td>");
                            for (int j = 0; j < vectorWithRows.size(); j++) {
                                out.println("<td>");
                                out.println(vectorWithRows.elementAt(j));
                                out.println("</td>");
                            }
                            out.println("</tr>");
                        }
                        out.println("</table>");
                        out.println("<br><button class=\"reset\" onclick=\"window.history.back();\">Go Back</button>");
                        out.println("</center></body>");
                        out.println("</html>");
                    } finally {
                        out.close();
                    }
                } else {
                    try {
                        printHeader("Student Details", out);
                        out.println("<h2>Student Does Not Exist</h2>");
                        out.println("<br><button class=\"button\" onclick=\"window.history.back();\">Go Back</button>&nbsp;&nbsp;<input class=\"reset\" type=\"button\" value=\"Close\" onclick=\"window.close();\" />");
                        out.println("</body>");
                        out.println("</html>");
                    } finally {
                        out.close();
                    }
                }

                break;
            case 4://Εμφάνιση των Ομάδων των μαθημάτων του καθηγητή
                //String userID = request.getParameter("profid");
                int idProfessor = Integer.parseInt(userID);
                Professors p = new Professors(idProfessor);
                Vector vectorWithProfessorCourses = new Vector();
                vectorWithProfessorCourses = p.viewProfessorCourses(userID);
                Vector vectorWithProfessorTeams = new Vector();
                Vector vectorWithTeamDetails = new Vector();
                Courses c1 = new Courses();

                if (p.checkIfProfessorExist(userID)) {
                    try {
                        printHeader("Professors Teams", out);
                        out.println("<center><h3><u>Professor Details</u></h3>");
                        out.println("<h4>" + p.getSurname() + " " + p.getName() + " (Professor ID : " + idProfessor + ")</h4>");
                        out.println("<h3><u>Professor Teams</u></h3><br>");
                        for (int i = 0; i < vectorWithProfessorCourses.size(); i++) {
                            c1 = (Courses) vectorWithProfessorCourses.elementAt(i);
                            out.println("<b>Course Code :</b>" + c1.getCourseId() + "<br>");
                            vectorWithProfessorTeams = p.getTeamPerCourse(c1.getCourseId());
                            if (vectorWithProfessorTeams.isEmpty()) {
                                out.println("No team has been created for this course!!<br><br>");
                            } else {

                                for (int j = 0; j < vectorWithProfessorTeams.size(); j++) {
                                    out.println("<br><b>Team Code :</b>" + vectorWithProfessorTeams.elementAt(j));
                                    vectorWithTeamDetails = p.viewTeamPerCourse((String) vectorWithProfessorTeams.elementAt(j));
                                    out.println("<table border=\"2\">");
                                    out.println("<thead>\n"
                                            + "  <tr>\n"
                                            + "     <th>A/A</th>\n"
                                            + "     <th>Student ID</th>\n"
                                            + "     <th>Surname</th>\n"
                                            + "     <th>Name</th>\n"
                                            + "  </tr>\n"
                                            + " </thead>");
                                    for (int l = 0; l < vectorWithTeamDetails.size(); l++) {
                                        out.println("<tr>");
                                        out.println("<td>" + (l + 1) + ". </td>");
                                        Vector teamRow = new Vector();
                                        teamRow = (Vector) vectorWithTeamDetails.elementAt(l);
                                        for (int m = 0; m < teamRow.size(); m++) {
                                            out.println("<td>");
                                            out.println(teamRow.elementAt(m));
                                            out.println("</td>");
                                        }
                                        out.println("</tr>");
                                    }
                                    out.println("</table>");
                                }
                            }
                        }
                        out.println("<br><button class=\"reset\" onclick=\"window.history.back();\">Go Back</button>");
                        out.println("</center></body>");
                        out.println("</html>");
                    } finally {
                        out.close();
                    }
                } else {
                    try {
                        printHeader("Professor Details", out);
                        out.println("<h2>Professor Does Not Exist</h2>");
                        out.println("<br><button class=\"button\" onclick=\"window.history.back();\">Go Back</button>&nbsp;&nbsp;<input class=\"reset\" type=\"button\" value=\"Close\" onclick=\"window.close();\" />");
                        out.println("</body>");
                        out.println("</html>");
                    } finally {
                        out.close();
                    }
                }
                break;

            case 5://login
                String uname = request.getParameter("uname");
                String pass = request.getParameter("pass");
                Users u = new Users();
                this.userID = u.returnUserID(uname, pass);
                if (u.loginUsers(uname, pass) == 0) {//login failed

                    printHeader("<center>Login Failed!!!", out);
                    out.println("<h2>Login Failed</h2>");
                    out.println("<br><button class=\"reset\" onclick=\"window.history.back();\">Go Back</center>");
                    out.println("</body>");
                    out.println("</html>");

                } else if (u.loginUsers(uname, pass) == 1) {//login 
                    String i = "1";
                    HttpSession session = request.getSession(true);
                    session.setAttribute("id", i);
                    session.setMaxInactiveInterval(100);
                    response.sendRedirect("cpanel.jsp");

                } else if (u.loginUsers(uname, pass) == 2) {
                    String i = "2";
                    HttpSession session = request.getSession(true);
                    session.setAttribute("id", i);
                    session.setMaxInactiveInterval(100);
                    response.sendRedirect("cpanel.jsp");

                }
                break;
            case 6://view professor courses

                int idProfessor1 = Integer.parseInt(userID);
                Professors p1 = new Professors(idProfessor1);
                Vector profCourses = new Vector();
                profCourses = p1.viewProfessorCourses(userID);
                try {
                    printHeader("Professor Courses", out);
                    out.println("<center><h1>Professor Courses</h1><br><br>");
                    out.println("<table border=\"2\">");
                    out.println("<thead>\n"
                            + "  <tr>\n"
                            + "     <th>A/A</th>\n"
                            + "     <th>Course Name</th>\n"
                            + "     <th>Course Code</th>\n"
                            + "  </tr>\n"
                            + " </thead>");
                    for (int i = 0; i < profCourses.size(); i++) {
                        out.println("<tr>");
                        Courses c = new Courses();
                        c = (Courses) profCourses.elementAt(i);
                        out.println("<td>" + (i + 1) + ". </td><td>" + c.getCourseName() + "</td><td>" + c.getCourseId() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<br><button class=\"reset\" onclick=\"window.history.back();\">Go Back</button>");
                    out.println("</center></body>");
                    out.println("</html>");
                } finally {
                    out.close();
                }

                break;
        }

    }

    void printHeader(String title, PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("<style>\n"
                + ".button{\n"
                + "    width: 300px;\n"
                + "    height: 35px;\n"
                + "    -webkit-border-radius: 5px;\n"
                + "    background-color: #a1c1ff;\n"
                + "}\n"
                + "\n"
                + ".reset{\n"
                + "    width: 150px;\n"
                + "    height: 35px;\n"
                + "    -webkit-border-radius: 5px;\n"
                + "    background-color: #ffa99d;\n"
                + "}\n"
                + "\n"
                + ".button:hover{\n"
                + "    background-color: #8bffa5;\n"
                + "    cursor: pointer;\n"
                + "}\n"
                + "\n"
                + ".reset:hover{\n"
                + "    background-color: #ff767b;\n"
                + "    cursor: pointer;\n"
                + "    -webkit-border-radius: 5px;\n"
                + "\n"
                + "}\n"
                + "\n"
                + "</style>");
        out.println("</head>");
        out.println("<body bgcolor=\"#e6e6fa\">");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //processRequest(request, response);
    }

}
