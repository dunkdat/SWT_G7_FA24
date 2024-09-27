import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JspToServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Không cần thực hiện gì trong phương thức này nếu không cần thiết
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Lấy URI của request
        String requestURI = httpRequest.getRequestURI();
        
        // Kiểm tra nếu request là từ một trang JSP
        if (requestURI.endsWith(".jsp") && !requestURI.endsWith("fix.jsp")) {
            // Tách bỏ phần đuôi .jsp để lấy servlet path
            String servletPath = requestURI.substring(httpRequest.getContextPath().length(), requestURI.length() - 4);
            // Forward tới servlet path
            httpRequest.getRequestDispatcher(servletPath).forward(request, response);
        } else {
            // Tiếp tục chuỗi xử lý cho các request khác
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Không cần thực hiện gì trong phương thức này nếu không cần thiết
    }
}
