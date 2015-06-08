<%
    if (session.getAttribute("id") != null) {
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>PAPEI JAVA NET PROGRAMMING</title>
        <link href="new_style.css" rel="stylesheet" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" type="text/javascript">
            function openCreateForm(){
                window.open("createTeam.html","_blank", 
                "toolbar=yes, scrollbars=yes, resizable=yes, top=200, left=400, width=600, height=400");
            }
                
        </script>
    </head>
    <body bgcolor="#e6e6fa">
<%
    if (session.getAttribute("id") == "1") {
        
%>
<div id="body">
<h1><u><center>STUDENTS ENVIRONMENT</center></u></h1><hr>
<div align="center" style="width: 300px;margin: 0 auto;" >
            <br><br><br>
            <input class="button" type="button" value="Create team" name="createTeam" onclick="openCreateForm()" /><br>
            <form method="GET" action="testServlet" name ="">
            <br><input class="button" type="submit" value="View Courses" name="showCourses" />
            <input type="hidden" name="function" value="2"/><br>
            </form>
            <form method="GET" action="testServlet" name ="">
            <br><input class="button" type="submit" value="View Your Teams" name="showCourses" />
            <input type="hidden" name="function" value="3"/><br>
            </form>
            <br><br><br><br>
            <input style="float: right;" type="button" class="reset"  value="Logout" name="Logout" onclick="location.href='logout.jsp'" />
    </div>
<br><br><br><br><br><br>
</div>
<%
}else if(session.getAttribute("id") == "2") {
%>
<div id="body">
    <h1><u><center>PROFESSORS ENVIRONMENT</center></u></h1><hr>
    <div align="center" style="width: 300px;margin: 0 auto;" >
        <form method="GET" action="testServlet" name ="">
        <input type="hidden" name="function" value="4"/><br>
        <input class="button" type="submit" value="View Teams for Each Course" name="createTeam" onclick="" />
        <br>
        </form>
        <form method="GET" action="testServlet" name ="">
        <input type="hidden" name="function" value="6"/><br>
        <input class="button" type="submit" value="View Professor Courses" name="createTeam" onclick="" />
        <br><br><br><br>
        <input style="float: right;" type="button" class="reset"  value="Logout" name="Logout" onclick="location.href='logout.jsp'" />
        </form>
    </div>
    <br><br><br><br>
</div>    
<%
}

}else{
%>
  <h1><u><center>YOU MUST LOGIN FIRST!!!</center></u></h1><hr>
<%
}
%>
</body>
</html>
