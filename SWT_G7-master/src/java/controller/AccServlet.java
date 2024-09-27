package controller;

import dal.LoginDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EmpAcc;
import model.MangAcc;

/**
 * Servlet implementation class AccServlet
 */
public class AccServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String role = request.getParameter("role");
            String newPass = request.getParameter("newPass");
            LoginDAO loginDAO = new LoginDAO();

            if ("mag".equals(role)) {
                int id = Integer.parseInt(request.getParameter("id"));
                MangAcc mangAcc = loginDAO.getMangAcc(id);
                if (newPass != null && !newPass.isEmpty()) {
                    String oldPass = request.getParameter("oldPass");
                    if (oldPass == null || !oldPass.equals(mangAcc.getPassword())) {
                        request.setAttribute("account", mangAcc.getAccount());
                        request.setAttribute("password", mangAcc.getPassword());
                        request.setAttribute("id", id);
                        request.setAttribute("dp", "1");
                        request.setAttribute("message", "Wrong old password!");
                        request.getRequestDispatcher("account.jsp").forward(request, response);
                    } else {
                        loginDAO.changeMagPass(id, newPass);
                        request.setAttribute("account", mangAcc.getAccount());
                        request.setAttribute("password", newPass); // Update password display
                        request.setAttribute("message", "Password changed successfully!");
                        request.getRequestDispatcher("account.jsp").forward(request, response);
                    }

                }
                request.setAttribute("account", mangAcc.getAccount());
                request.setAttribute("password", mangAcc.getPassword());
                request.getRequestDispatcher("account.jsp").forward(request, response);
            } else if ("staff".equals(role)) {
                String id = request.getParameter("id");
                EmpAcc empAcc = loginDAO.getEmpAcc(id);
                if (newPass != null && !newPass.isEmpty()) {
                    String oldPass = request.getParameter("oldPass");
                    if (oldPass == null || !oldPass.equals(empAcc.getPassword())) {
                        request.setAttribute("account", empAcc.getAccount());
                        request.setAttribute("password", empAcc.getPassword());
                        request.setAttribute("id", id);
                        request.setAttribute("dp", "1");
                        request.setAttribute("message", "Wrong old password!");
                        request.getRequestDispatcher("account.jsp").forward(request, response);
                    } else {
                        loginDAO.changeEmpPass(id, newPass);
                        request.setAttribute("account", empAcc.getAccount());
                        request.setAttribute("password", newPass); // Update password display
                        request.setAttribute("message", "Password changed successfully!");
                        request.getRequestDispatcher("account.jsp").forward(request, response);
                    }
                }
                request.setAttribute("account", empAcc.getAccount());
                request.setAttribute("password", empAcc.getPassword());
                request.getRequestDispatcher("account.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("account.jsp").forward(request, response);
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
        return "Account Management Servlet";
    }
}
