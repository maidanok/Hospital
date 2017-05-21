package by.hospital.filters;


import by.hospital.command.Command;
import by.hospital.command.CommandFactory;
import by.hospital.domain.Staff;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Pasha on 10.05.2017.
 */
public class SecurityFilter implements Filter {
    private static Logger logger = Logger.getLogger(SecurityFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            CommandFactory commandFactory = CommandFactory.getInstance();
            Command command = commandFactory.getCommand(httpServletRequest);
            Set<Post> allowPost = command.getAllowPosts();
            HttpSession session = httpServletRequest.getSession(false);

            Staff user = new Staff();
            String userName = "unauthorized user";
            if(session!=null){
                user = (Staff) session.getAttribute("user");
            }
            boolean access = false;
            if (user!=null && allowPost != null) {
                access = allowPost.contains(user.getPost());
                userName = user.getLogin();
            }
            if (allowPost == null) {
                access = true;
            }
            if(access){
                chain.doFilter(request,response);
            }else {
                String message = "Accessdenied";
                request.setAttribute("message",message);
                logger.info(String.format("Trying of access to forbidden resource \"%s\"", userName, command));
                httpServletRequest.getServletContext().getRequestDispatcher("/"+ConfigurationManager.getProperty("PAGE_ERROR")).forward(request,response);
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
        }
    }

    @Override
    public void destroy() {

    }
}
