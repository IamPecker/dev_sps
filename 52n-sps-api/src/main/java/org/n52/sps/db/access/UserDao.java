package org.n52.sps.db.access;

import org.n52.sps.user.User;

/**
 * Created by xiangbaoqing on 2017/3/13.
 */
public interface UserDao {
    public User getInstance(String userId);

    public User getInstance(String userId, String passWord);

    public long saveInstance(User instance);

    public long updateInstance(User instance);
}
