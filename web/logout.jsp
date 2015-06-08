<%
session.setAttribute("id", null);
session.invalidate();
response.sendRedirect("index.jsp");
%>
