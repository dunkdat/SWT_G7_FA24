/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.EmpAcc;
import model.MangAcc;

/**
 *
 * @author DAT
 */
public class LoginServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String acc = request.getParameter("acc");
            String pass = request.getParameter("pass");
            String log = request.getParameter("log");
            boolean in = false;
            LoginDAO l = new LoginDAO();
            if(log!=null){
            if(log.equals("staff")){
                for(EmpAcc x : l.getEmpAcc()){
                    if(x.getAccount().equals(acc) && x.getPassword().equals(pass)){
                        HttpSession session = request.getSession();
                    session.setAttribute("id", x.getId());
                    session.setAttribute("name", x.getName());
                    request.getRequestDispatcher("staff").forward(request, response);
                    in = true;
                    break;
                    }
                }
                if(!in){
                    request.setAttribute("message", "Wrong user name or password, try again!");
             request.getRequestDispatcher("login.jsp").include(request, response);
             return;
                }
            }
            if(log.equals("mag")){
                for(MangAcc x : l.getMangAcc()){
                    if(x.getAccount().equals(acc) && x.getPassword().equals(pass)){
                        HttpSession session = request.getSession();
                        session.setAttribute("id", x.getId());
                        session.setAttribute("name", x.getName());
                    request.getRequestDispatcher("mag").forward(request, response);
                    in = true;
                    break;
                    }
                }
                if(!in){
                    request.setAttribute("message", "Wrong user name or password, try again!");
             request.getRequestDispatcher("login.jsp").include(request, response); 
             return;
                }
            }
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
