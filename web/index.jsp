<%-- 
    Document   : indexjsp
    Created on : 2 Ιουλ 2014, 8:14:18 πμ
    Author     : lopmind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="new_style.css" rel="stylesheet" type="text/css" />
        <title>Java Net Authentication</title>
    </head>
    <body bgcolor="#e6e6fa" style="width:800px;border: solid 7px #fff;margin: 0 auto;">
        <form method="get" action="testServlet">
            <center>
            
            <h1>Προγραμματισμός στο Διαδίκτυο και τον Παγκόσμιο ιστό</h1>
            <hr style="width: 800px;">
            </center>     

           <div style="width: 800px;float: left;">
           <br><b>Τελική Εργασία : </b> Δημιουργία 3-tier εφαρμογής
           <br><b>Ημερομηνία Παράδοσης : </b> 10 Iουλ 2014<br>
           <br><b>Ομάδα Εργασίας :</b> 
               <ol>
                   <li>Μπεζαΐτης Βασίλειος (Π12186)</li>
                   <li>Κουσαλίδης Πολυχρόνης (Π12189)</li>
                   <li>Κορδής Κωνσταντίνος (Π09040)</li>
                   <li>Ρούσσος Βελισσάριος (Π12134)</li>
               </ol>
           <br><b>Παρατηρήσεις:</b>
                <ol>
                    <li>Πληροφορίες σύνδεσης <a onclick="javascipt:window.open('pdf/manual.pdf','_blank', 
                'toolbar=yes, scrollbars=yes, resizable=yes, top=50, left=50, width=600, height=600');">εδώ.</a></li>
                    <li>Υλοποιήθηκαν οι λειτουργίες που ζητούνται στην <a onclick="javascipt:window.open('pdf/final_exercise.pdf','_blank', 
                'toolbar=yes, scrollbars=yes, resizable=yes, top=50, left=50, width=600, height=600');">εκφώνηση</a> της άσκησης.</li>
                   <li>Το σχήμα της βάσης δεδομένων τροποποιήθηκε σε σχέση με την Εργασία 2 και στήθηκε σε XAMPP με όνομα papeijava.sql .</li>
                   <li>Στην ΒΔ έχουν εισαχθεί ήδη κάποιες εγγραφές για την διευκόλυνση του ελέγχου των λειτουργιών της εφαρμογής.</li>
                   <li>Λεπτομέρειες για την εργασία αναφέρονται στο <a onclick="javascipt:window.open('pdf/javanet.pdf','_blank', 
                       'toolbar=yes, scrollbars=yes, resizable=yes, top=50, left=50, width=600, height=600');">documentation</a> της εφαρμογής.</li>
                </ol>
           </div>
            <center>
            <table border="1" width="30%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="2">Login Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>User Name</td>
                        <td><input class="texts" type="text" name="uname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input class="texts" type="password" name="pass" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="hidden" name="function" value="5"/>
                            <input class="button" type="submit" value="Login" /></td>
                        <td><input class="reset" type="reset" value="Reset" /></td>
                    </tr>
                </tbody>
            </table>
                </center>
        </form>
    </body>
</html>
