/**
 * ﻿Copyright (C) 2012-${latestYearOfContribution} 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as publishedby the Free
 * Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of the
 * following licenses, the combination of the program with the linked library is
 * not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed under
 * the aforementioned licenses, is permitted by the copyright holders if the
 * distribution is compliant with both the GNU General Public License version 2
 * and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */
package org.n52.sps.db.access;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.n52.sps.sensor.model.SensorTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class HibernateSensorTaskDao extends HibernateDaoSupport implements SensorTaskDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateSensorTaskDao.class);

    public SensorTask getInstance(String taskId) {
        LOGGER.debug("getInstance({})", taskId);
        Query query = getCurrentSession().createQuery("from SensorTask as st where st.taskId = :taskId");
        query.setParameter("taskId", taskId);
        return (SensorTask) query.uniqueResult();
    }

    public Iterable<SensorTask> getAllInstances() {
        LOGGER.debug("getAllInstances()");
        return getCurrentSession().createQuery("from SensorTask").list();
    }

    public void saveInstance(SensorTask instance) {
        LOGGER.debug("saveInstance({})", instance);
        getCurrentSession().save(instance);
    }

    public void updateInstance(SensorTask instance) {
        LOGGER.debug("updateInstance({})", instance);
        getCurrentSession().update(instance);
    }

    public void deleteInstance(SensorTask instance) {
        LOGGER.debug("deleteInstance({})", instance);
        // TODO Auto-generated method stub
        getCurrentSession().delete(instance);
        /*throw new UnsupportedOperationException();*/

    }

    public SensorTask findBy(String procedure, String taskId) {
        LOGGER.debug("findBy({}, {})", procedure, taskId);
        // TODO Auto-generated method stub
        /*throw new UnsupportedOperationException();*/
        Query query = getCurrentSession().createQuery("from SensorTask as st where st.procedure = :procedure and st.taskId = :taskId");
        query.setParameter("procedure", procedure);
        query.setParameter("taskId", taskId);
        return (SensorTask) query.uniqueResult();

    }

    public Iterable<SensorTask> findByTaskId(String taskId) {
        LOGGER.debug("findByTaskId({})", taskId);
        // TODO Auto-generated method stub
        /*throw new UnsupportedOperationException();*/
        Query query = getCurrentSession().createQuery("from SensorTask as st where st.taskId = :taskId");
        query.setParameter("taskId", taskId);
        return query.list();

    }
    
    public Iterable<SensorTask> findByProcedure(String procedure){
   	 LOGGER.debug("findByTaskProcedure({})", procedure);
   	  Query query = getCurrentSession().createQuery("from SensorTask as st where st.procedure = :procedure");
         query.setParameter("procedure", procedure);
         return query.list();
   }

    public long getCount(String procedure) {
        LOGGER.debug("getCount({})", procedure);
        return ((Long)getCurrentSession().createQuery("select count(st) from SensorTask st").uniqueResult()).longValue();
    }

    private Session getCurrentSession() {
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }
}
