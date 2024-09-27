package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
    public static final int BUFFER_SIZE = 1024 * 100;
    public static final String UPLOAD_DIR = "resources";
    public static String fileName = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        fileName = request.getParameter("name");
        if (fileName == null || fileName.equals("")) {
            response.setContentType("text/html");
            response.getWriter().println("<h3>File " + fileName + " Is Not Present!</h3>");
            return;
        }

        String applicationPath = getServletContext().getRealPath("");
        String downloadPath = applicationPath + File.separator + UPLOAD_DIR;
        String filePath = downloadPath + File.separator + fileName;
        System.out.println("fileName: " + fileName);
        System.out.println("filePath: " + filePath);

        File file = new File(filePath);
        if (!file.exists()) {
            response.setContentType("text/html");
            response.getWriter().println("<h3>File " + fileName + " Is Not Present .....!</h3>");
            return;
        }

        response.setContentType("application/octet-stream");
        response.setContentLength((int) file.length());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
        response.setHeader(headerKey, headerValue);

        try (FileInputStream inputStream = new FileInputStream(file);
             OutputStream outStream = response.getOutputStream()) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            outStream.flush();
        } catch (IOException ioExObj) {
            System.out.println("Exception While Performing The I/O Operation: " + ioExObj.getMessage());
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
        return "Short description";
    }
}
