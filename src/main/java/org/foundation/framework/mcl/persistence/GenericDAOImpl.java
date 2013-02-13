package org.foundation.framework.mcl.persistence;



import org.foundation.Foundation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Copyright (C) 2013 by Scott Byrns
 * http://github.com/scottbyrns
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p/>
 * Created 1/31/13 3:49 PM
 */
public abstract class GenericDAOImpl<E extends IModel<ID>, ID extends Serializable> extends Foundation implements GenericDAO<E, ID>
{
    //   protected static final Log log = LogFactory.getLog(GenericDAOImpl.class);

    protected Class<? extends E> entityClass;

    protected Session session = null;//HibernateUtilities.getSessionFactory().getCurrentSession();



//    public GenericDAOImpl(Class<? extends E> _entityClass)
//    {
//
//        entityClass = _entityClass;
        //      HibernateUtil.beginTransaction();
//    }


    public E getById(ID id)
    {

        if (null == id)
        {
            return null;
        }

        @SuppressWarnings("unchecked") E entity = (E) session.get(entityClass,
                                                                  id);
        return entity;
    }


    public E getByExample(E example)
    {

        Criteria crit = session.createCriteria(entityClass).add(Example.create(example));

        @SuppressWarnings("unchecked") E entity = (E) crit.uniqueResult();

        return entity;
    }


    public List<E> getAll()
    {

        return getAll(true,
                      true);
    }


    public List<E> getAll(boolean cacheable, boolean sort)
    {

        Criteria crit = session.createCriteria(entityClass).setCacheable(cacheable);

        @SuppressWarnings("unchecked") List<E> entities = crit.list();

        if (sort)
        {
            Collections.sort(entities);
        }

        return Collections.unmodifiableList(entities);
    }


    public List<E> getAllByExample(E example)
    {

        return getAllByExample(example,
                               false,
                               true);
    }


    public List<E> getAllByExample(E example, boolean cacheable, boolean sort)
    {

        Criteria crit = session.createCriteria(entityClass).add(Example.create(example)).setCacheable(cacheable);

        @SuppressWarnings("unchecked") List<E> entities = crit.list();

        if (sort)
        {
            Collections.sort(entities);
        }

        return Collections.unmodifiableList(entities);
    }


    public int getCount()
    {

        Criteria crit = session.createCriteria(entityClass).setProjection(Projections.rowCount());

        return (Integer) crit.uniqueResult();
    }


    public int getCount(E entity)
    {

        Criteria crit = session.createCriteria(entityClass).setProjection(Projections.rowCount()).add(Example.create(entity));

        return (Integer) crit.uniqueResult();
    }


    public boolean isExists(E entity)
    {

        return getCount(entity) > 0;
    }


    public void save(E entity)
    {
        //      if (log.isInfoEnabled())
        //         log.info("Persisting " + entity);

        if (!session.isOpen()) {
            session = HibernateUtilities.getSessionFactory().getCurrentSession();
        }

        Transaction tx = session.beginTransaction();

        //        try {

        /**
         * If you want to transfer state from entity a to entity b, you can do the following:
         *
         * <code>
         *      entityManager.merge(a);
         *      entityManager.refresh(b);
         * </code>
         */
        //            E otherEntity = getById(entity.getId());
        //
        //            if (null != otherEntity)
        //            {
        //                session.merge(entity);
        //                session.refresh(entity);
        //                session.saveOrUpdate(otherEntity);
        //            }
        //            else {
        session.saveOrUpdate(entity);
        //            }

        tx.commit();

    }


    public void saveAll(List<E> entities)
    {

        int counter = 0;
        Transaction tx = session.beginTransaction();
        // loop over entities, and save them all.
        for (E entity : entities)
        {
            session.saveOrUpdate(entity);
            counter++;
            // make sure the memory doesn't overflow
            if (counter % 500 == 0)
            {
                session.flush();
                session.clear();
            }
        }
        tx.commit();
    }


    public void delete(E entity)
    {

        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
    }

//
//    public static void attach(Object entity)
//    {
//
//        if (entity != null)
//        {
//            Session session = HibernateUtilities.getSessionFactory().getCurrentSession();
//
//            if (!session.contains(entity))
//            //            session.lock(entity, LockMode.NONE);
//            {
//                session.buildLockRequest(LockOptions.NONE).lock(entity);
//            }
//        }
//    }
//
//
//    public static void detach(Object entity)
//    {
//
//        if (entity != null)
//        {
//            Session session = DataConnector.getInstance().getSession();
//            session.flush();
//            session.evict(entity);
//        }
//    }
}

