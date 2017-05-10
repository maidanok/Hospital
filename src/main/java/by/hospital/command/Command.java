package by.hospital.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 07.05.2017.
 */
public interface Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
