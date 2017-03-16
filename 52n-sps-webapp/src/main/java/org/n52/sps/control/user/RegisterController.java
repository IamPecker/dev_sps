package org.n52.sps.control.user;

import org.n52.sps.service.user.UserService;
import org.n52.sps.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

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
        response.setContentType("text/html;charset=utf-8");
        String userId = request.getParameter("userId");
        String passWord = request.getParameter("passWord");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String xmpp = request.getParameter("xmpp");
        String fax = request.getParameter("fax");
        User user = new User();
        user.setUserID(userId);
        user.setPassWord(passWord);
        user.setPhone(phone);
        user.setEmail(email);
        user.setXmpp(xmpp);
        user.setFax(fax);
        Calendar now = Calendar.getInstance();
        user.setCreatedTime(now);
        user.setLatestLoginTime(now);
        user.setUpdatedTime(now);
        if(userService.saveUser(user))
        {
            try {
                response.sendRedirect(request.getContextPath()+"/spsClient.html");
            } catch (IOException e) {
                LOGGER.error("Redirect action to spsClient.html failed", e);
            }
        } else{
            try {
                response.sendRedirect(request.getContextPath() + "/loginError.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
