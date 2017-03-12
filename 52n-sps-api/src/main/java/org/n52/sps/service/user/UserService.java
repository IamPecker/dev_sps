package org.n52.sps.service.user;

import org.n52.sps.user.User;

/**
 * Created by xiangbaoqing on 2017/3/13.
 */
public interface UserService {
    public boolean isUserValid(String userId, String passWord);

    public boolean isUserExist(String userId);

    public boolean saveUser(User user);

    public boolean updateUser(User user);
}
