package Servlets;

import Hash.MD5_Hash;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class saveUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userType = "user";
        String url = "jdbc:mysql://localhost:3306/tp_auth";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement stmt = connection.createStatement();
            ResultSet nbUsersQ = stmt.executeQuery("select Count(*) FROM user");
            int nbUsers = 0;
            while (nbUsersQ.next()) {
                nbUsers = nbUsersQ.getInt(1) + 1;
            }

            String idUser = userType + "-" + nbUsers;

            String name = request.getParameter("name");
            request.setAttribute("name", name);

            String fname = request.getParameter("fname");
            request.setAttribute("fname", fname);

            int age = Integer.parseInt(request.getParameter("age"));
            request.setAttribute("age", age);


            String idAuth = "auth-" + idUser;

            String userName = request.getParameter("userName");
            request.setAttribute("userName", userName);

            String password = request.getParameter("password");

            MD5_Hash md5 = new MD5_Hash();
            String encryptedUserName = md5.encrypt(userName);
            String encryptedPassword = md5.encrypt(password);

            stmt.executeUpdate("INSERT INTO `user` (`id`, `nom`, `prenom`, `age`, `type`) VALUES ('" + idUser + "', '" + name + "', '" + fname + "', '" + age + "', '" + userType + "');");
            stmt.executeUpdate("INSERT INTO `auth` (`username`, `password`, `idAuth`, `idUser`) VALUES ('" + encryptedUserName + "', '" + encryptedPassword + "', '" + idAuth + "', '" + idUser + "');");

            String page = "userCreated.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);

        } catch (ServletException | IOException | ClassNotFoundException | NumberFormatException | SQLException e) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error</title>");
            out.println("<style>body{background:gray;color:white}</style>");
            out.println("</head>");
            out.println("<body align=\"center\" >");
            out.println("<h1>Error has occured:</h1>");
            if (e.getMessage().substring(0, 15).equals("Duplicate entry")) {
                out.println("<h3>UserName already used</h3>");
            } else {
                out.println("<h3>" + e.getMessage() + "</h3>");
            }
            out.println("</body>");
            out.println("</html>");
        }

    }
}
