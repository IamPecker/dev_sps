package org.n52.sps.service.user;

import org.n52.sps.db.access.UserDao;
import org.n52.sps.user.User;

/**
 * Created by xiangbaoqing on 2017/3/13.
 */
public class UserServiceImp implements UserService {
    private UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public boolean isUserValid(String userId, String passWord) {
        User user = userDao.getInstance(userId, passWord);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isUserExist(String userId) {
        User user = userDao.getInstance(userId);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean saveUser(User user) {
        long i = userDao.saveInstance(user);
        if (i < 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        long i = userDao.updateInstance(user);
        if (i < 0) {
            return false;
        }
        return true;
    }
}
