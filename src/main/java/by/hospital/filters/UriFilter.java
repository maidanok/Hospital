package by.hospital.filters;

import by.hospital.prop_managers.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Pasha on 11.05.2017.
 */
public class UriFilter implements Filter {
    private static Logger logger = Logger.getLogger(UriFilter.class);
    private static Map<String, String> links = new ConcurrentHashMap<>();

    static {
        links.put("/login", "Login");
        links.put("/hospital", "OpenHospital");
        links.put("/directories", "OpenDirectories");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String contextPath = httpServletRequest.getContextPath();
            String uri = httpServletRequest.getRequestURI();

            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String commandName = null;
            if (endAction >= 0) {
                commandName = uri.substring(beginAction, endAction);
            } else {
                commandName = uri.substring(beginAction);
            }
            String command = links.get(commandName);
            if (command != null) {

                request.setAttribute("COMMAND", command);
                request.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty(command)).forward(request, response);
            }
            chain.doFilter(request, response);
        } else {
            request.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("PAGE_ERROR")).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
