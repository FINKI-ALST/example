package mk.ukim.finki.wp.lab.web.filter;

import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile("servlet")
@WebFilter
public class CourseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse)servletResponse;
        if(!req.getServletPath().equals("/courses")  && !req.getServletPath().equals("/courses/add-form") &&!req.getServletPath().equals("/courses/add") && !req.getServletPath().contains("/courses/edit-form") && !req.getServletPath().contains("/courses/delete") && req.getSession().getAttribute("courseId")==null)
        {
            resp.sendRedirect("/courses");
        }
        else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
