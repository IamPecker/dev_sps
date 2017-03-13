package org.n52.sps.db.access;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.n52.sps.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xiangbaoqing on 2017/3/13.
 */
@Transactional
public class HibernateUserDao extends HibernateDaoSupport implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUserDao.class);

    public User getInstance(String userId) {
        LOGGER.info("getInstance({})", userId);
        Query query = getCurrentSession().createQuery("from User where userID = :userID");
        query.setParameter("userID", userId);
        return (User) query.uniqueResult();
    }

    public User getInstance(String userId, String passWord) {
        LOGGER.info("getInstance({})", userId);
        Query query = getCurrentSession().createQuery("from User where userID = :userID and passWord = :passWord");
        query.setParameter("userID", userId);
        query.setParameter("passWord", passWord);
        return (User) query.uniqueResult();
    }

    public long saveInstance(User instance) {
        LOGGER.debug("saveInstance({})", instance);
        getCurrentSession().save(instance);
        return 0;
    }

    public long updateInstance(User instance) {
        LOGGER.debug("updateInstance({})", instance);
        getCurrentSession().update(instance);
        return 0;
    }

    private Session getCurrentSession() {
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }
}
