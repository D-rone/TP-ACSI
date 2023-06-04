package Servlets;

import Hash.MD5_Hash;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String url = "jdbc:mysql://localhost:3306/tp_auth";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement stmt = connection.createStatement();

            String userName = request.getParameter("userName");
            String password = request.getParameter("password");

            MD5_Hash md5 = new MD5_Hash();
            String encryptedUserName = md5.encrypt(userName);
            ResultSet user = stmt.executeQuery("select count(*), userName , password FROM auth where(userName=\"" + encryptedUserName + "\");");

            String found = "";
            String resPassword = "";
            while (user.next()) {
                found = user.getString(1);
                resPassword = user.getString(3);
            }
            if (found.equals("1")) {
                if (md5.encrypt(password).equals(resPassword)) {
                    request.setAttribute("userName", userName);
                    ResultSet userData = stmt.executeQuery("SELECT id,nom,prenom,age,type FROM `auth`, `user` WHERE (auth.idUser = user.id AND userName=\"" + encryptedUserName + "\");");

                    while (userData.next()) {
                        request.setAttribute("id", userData.getString(1));
                        request.setAttribute("name", userData.getString(2));
                        request.setAttribute("fname", userData.getString(3));
                        request.setAttribute("age", userData.getString(4));
                        request.setAttribute("type", userData.getString(5));
                    }
                    String page = "userPage.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                    dispatcher.forward(request, response);
                } else {
                    throw new Exception("Wrong Password");
                }
            } else {
                if (found.equals("0")) {
                    throw new Exception("Unfound userName");
                }
            }
        } catch (Exception e) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error</title>");
            out.println("<style>body{background:gray;color:white;}</style>");
            out.println("</head>");
            out.println("<body align=\"center\" >");
            if (!e.getMessage().equals("Wrong Password") || !e.getMessage().equals("Unfound userName")) {
                out.println("<h1>Error has occured:</h1>");
            }
            out.println("<h3>" + e.getMessage() + "</h3>");
            out.println("</body>");
            out.println("</html>");
        }

    }
}
