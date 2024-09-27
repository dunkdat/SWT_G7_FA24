/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ManagerDAO;
import dal.ProjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import model.DoneProjects;
import model.Projects;

/**
 *
 * @author DAT
 */
public class ManageFileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession ss = request.getSession(false);
            if (ss != null && ss.getAttribute("id")!=null) {
                ProjectDAO s = new ProjectDAO();
                ManagerDAO m = new ManagerDAO();
                String sub = request.getParameter("submit");
                String pro_id = request.getParameter("id");
                String name = request.getParameter("pro_name");
                String emp_id = request.getParameter("emp_id");
                String details = request.getParameter("details");
                String dead_line = request.getParameter("dead_line");
                String searchBy = request.getParameter("searchBy");
                String searchVal = request.getParameter("searchVal");
                String tag = request.getParameter("tag");
                String type = request.getParameter("type");
                if (sub != null) {

                    if (sub.equals("addPro")) {
                        if(m.existedProject(pro_id)&&!m.existedStaff(emp_id)&&!m.checkDeadline(dead_line)){
                            request.setAttribute("error", "Project existed!"
                                    + "Staff does not existed!"
                                    + "Dead line must >= today!");
                        }
                        else if(m.existedProject(pro_id)){
                            request.setAttribute("error", "Project existed!");
                        }
                        else if(!m.existedStaff(emp_id)){
                            request.setAttribute("error", "Staff does not exist!");
                        }
                        else if(!m.checkDeadline(dead_line)){
                            request.setAttribute("error", "Dead line must >= today!");
                        }
                        else{
                            m.addProject(new Projects(pro_id, name, emp_id, details, dead_line));
                        }
                        
                    }
                    if (sub.equals("del")) {
                        m.deleteProject(pro_id);
                    }
                    if (sub.equals("fix")) {
                        m.fixProject(pro_id, name, emp_id, details, dead_line);
                    }
                    if(sub.equals("reass")){
                        if(!m.checkDeadline(dead_line)){
                            request.setAttribute("error1", "Dead line must >= today!");
                        }else{
                            s.deleteDoneProject(pro_id);
                            m.fixProject(pro_id, dead_line);
                        }
                        
                    }
                    if (sub.equals("search")) {
                        if (searchBy.equals("1")) {
                            List<Projects> t = s.PgetAllByP(searchVal);
                            List<DoneProjects> l = s.DPgetAllByP(searchVal);
                            request.setAttribute("tdata", t);
                            request.setAttribute("sdata", l);
                            request.setAttribute("searchBy", searchBy);
                            request.setAttribute("searchVal", searchVal);
                            request.getRequestDispatcher("mag.jsp").forward(request, response);
                        }
                        if (searchBy.equals("2")) {
                            List<Projects> t = s.PgetAllByE(searchVal);
                            List<DoneProjects> l = s.DPgetAllByE(searchVal);
                            request.setAttribute("tdata", t);
                            request.setAttribute("sdata", l);
                            request.setAttribute("searchBy", searchBy);
                            request.setAttribute("searchVal", searchVal);
                            request.getRequestDispatcher("mag.jsp").forward(request, response);
                        }
                    }
                    
                    if(sub.equals("getAll")){
                        List<Projects> t = m.getAll();
                List<DoneProjects> l = s.getAll();
                request.setAttribute("tdata", t);
                request.setAttribute("sdata", l);
                request.getRequestDispatcher("mag.jsp").forward(request, response);
                    }
                }
                List<Projects> t = m.getAll();
                List<DoneProjects> l = s.getAll();
                request.setAttribute("tdata", t);
                request.setAttribute("sdata", l);
                ss.setAttribute("id", ss.getAttribute("id"));
                ss.setAttribute("name", ss.getAttribute("name"));
                request.getRequestDispatcher("mag.jsp").forward(request, response);
            } else {

                request.setAttribute("message", "Please login first!");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
