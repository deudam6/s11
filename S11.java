import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class servletDatabase extends HttpServlet 
{ 
 Connection cn;
 public void init()
 {
 try
 {
 Class.forName("org.gjt.mm.mysql.Driver");
 cn=DriverManager.getConnection("jdbc:mysql://localhost/stud
","root","password");
 System.out.println("Hii");
 }
 catch(Exception ce)
 { 
 System.out.println("Error"+ce.getMessage());
 }
 
 }
 public void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException 
 {
 resp.setContentType("text/html");
 PrintWriter pw=resp.getWriter(); 
 try
 {
 int rno=Integer.parseInt(req.getParameter("t1")); 
 String qry="Select * from student where 
rollno="+rno; 
 Statement st=cn.createStatement();
 ResultSet rs=st.executeQuery(qry); 
 while(rs.next())
 {
 pw.print("<table border=1>");
 pw.print("<tr>");
 pw.print("<td>" + rs.getInt(1) + "</td>");
 pw.print("<td>" + rs.getString(2) + "</td>");
 pw.print("<td>" + rs.getFloat(3) + "</td>");
 pw.print("</tr>");
 pw.print("</table>");
 }
 }
 catch(Exception se){}
 pw.close();
 }
} 
HTML File 
<html>
 <body>
 <form action="http://localhost:8080/servDb/servletDatabase"
method="get">
 Enter Roll No:<input type="text" name="t1">
 <input type="submit">
 </form>
 </body>
</html>
pssql> create database stud;
Query OK, 1 row affected (0.00 sec)
 
pssql> create table student(rollno int primary key,name
text,percentage float);
Query OK, 0 rows affected (0.07 sec)
pssql> insert into student values(1,'student1',79);
Query OK, 1 row affected (0.04 sec)
pssql> insert into student values(2,'student2',69);
Query OK, 1 row affected (0.05 sec)
pssql> insert into student values(3,'student3',58);
Query OK, 1 row affected (0.06 sec)
 
pssql> select * from student;
















import java.sql.*;
import java.io.*;
public class ResultSetMetaData
{
 public static void main(String[] args) throws Exception
 {
 
 
 Statement stmt;
 Class.forName("org.postgresql.Driver");
 Connection conn = 
DriverManager.getConnection("jdbc:postgresql://localhost/stud","postgre
s","password");
 stmt = conn.createStatement();
 ResultSet rs = stmt.executeQuery("Select * from student");
 java.sql.ResultSetMetaData rsmd = rs.getMetaData();
 int noOfColumns = rsmd.getColumnCount();
 System.out.println("Number of columns = " + noOfColumns);
 for(int i=1; i<=noOfColumns; i++)
 {
 System.out.println("Column No : " + i);
 System.out.println("Column Name : " + rsmd.getColumnName(i));
 System.out.println("Column Type : " + rsmd.getColumnTypeName(i));
 System.out.println("Column display size : " + 
rsmd.getColumnDisplaySize(i));
 } 
 conn.close();
 }
}