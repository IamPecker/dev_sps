package org.n52.sps.control.user;

import org.n52.sps.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xiangbaoqing on 2017/3/13.
 */
@Controller
public class RegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String passWord = request.getParameter("passWord");
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            if (userService.isUserValid(userId, passWord)) {
                try {
                    response.sendRedirect(request.getContextPath() + "/spsClient.html");
                } catch (IOException e) {
                    LOGGER.error("Redirect acton to spsClient.html failed", e);
                } finally {
                    out.print("user exists");
                }
            }
            out.print("user not exist");
            response.sendRedirect(request.getContextPath() + "/loginError.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
