package by.hospital.command;

import by.hospital.prop_managers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * Created by Admin on 07.05.2017.
 */
public class NoCommand implements Command {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws ServerException, IOException {
        String page = ConfigurationManager.getProperty("PAGE_LOGIN");
        return page;
    }
}
