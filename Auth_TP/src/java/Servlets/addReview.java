package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet(name = "addReview", urlPatterns = {"/addReview"})
public class addReview extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/tp_auth";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement stmt = connection.createStatement();
            String sender = (String) request.getParameter("user");
            String text = (String) request.getParameter("reviewTxt");
            if (text.length() != 0) {
                stmt.executeUpdate("INSERT INTO `comment` VALUES ('" + sender + "', '" + text + "');");
            }

            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Success</title>");
            out.println("<style>body{background:gray;color:white;}</style>");
            out.println("</head>");
            out.println("<body align=\"center\" >");
            out.println("<button onclick=\"history.back()\">&lt;</button>");
            out.println("Review submitted Successfully ");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {

            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Success</title>");
            out.println("<style>body{background:gray;color:white;}</style>");
            out.println("</head>");
            out.println("<body align=\"center\" >");
            out.println("<button onclick=\"history.back()\">&lt;</button>");
            out.println("An Error has occured"+e.getMessage());
            out.println("</body>");
            out.println("</html>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
