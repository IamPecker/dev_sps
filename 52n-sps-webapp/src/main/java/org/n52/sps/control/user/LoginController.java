package org.n52.sps.control.user;


import org.n52.sps.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by xiangbaoqing on 2017/3/13.
 */
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HttpServletRequest login(HttpServletRequest request, HttpServletResponse response) {
        Map parameters = request.getParameterMap();
        String userId = (String) parameters.get("userId");
        String passWord = (String) parameters.get("password");
        if (userService.isUserValid(userId, passWord)){
            return
        }
        return null;
    }
}
