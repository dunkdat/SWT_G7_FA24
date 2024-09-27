package controller;

import dal.ManagerDAO;
import dal.ProjectDAO;
import dal.StaffDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DoneProjects;
import model.Projects;

/**
 * Handles staff-related operations.
 * 
 * Author: DAT
 */
public class StaffServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false); // Don't create a new session
        if (session != null && session.getAttribute("id") != null) {
            
            StaffDAO staffDAO = new StaffDAO();
            ProjectDAO projectDAO = new ProjectDAO();
            String id = (String) session.getAttribute("id");

            List<Projects> projectList = staffDAO.getAll(id);
            List<DoneProjects> doneProjectsList = projectDAO.getAll();
            
            session.setAttribute("id", id);
            request.setAttribute("tdata", doneProjectsList);
            request.setAttribute("sdata", projectList);

            request.getRequestDispatcher("staff.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Please login first!");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles staff-related operations.";
    }
}
