package Servlets;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
@WebServlet(name = "submitReview", urlPatterns = {"/submitReview"})
public class submitReview extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reviews = "";
        String url = "jdbc:mysql://localhost:3306/tp_auth";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement stmt = connection.createStatement();
            ResultSet comments= stmt.executeQuery("SELECT * FROM `comment`");
            while(comments.next()){
                String sender = comments.getString(1);
                String text = comments.getString(2);
                reviews += "<div><b>"+sender+ " :</b> <div class='comment'>"+ text + "</div></div><br/><br/>";
            }
            reviews+="<div id='to'></div>";
        } catch (Exception e) {
        }
        
        String user = request.getParameter("user");
        String type = request.getParameter("type");
        request.setAttribute("reviews", reviews);
        String input = "<form action=\"addReview\"><input type=\"text\" name=\"reviewTxt\" required/><input style=\"display:none\" type=\"text\" value=\"" + user + "\" name=\"user\" /><input type=\"submit\" value=\"submit\" class=\"addReview\"></form>";

        request.setAttribute("input", (type.equals("admin")) ? "" : input);

        String page = "reviews.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }

}
