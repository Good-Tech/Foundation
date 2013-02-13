package org.foundation.warehouse;



import org.foundation.Foundation;
import org.foundation.framework.mcl.persistence.HibernateUtilities;
import org.foundation.ml.Label;
import org.hibernate.PropertyValueException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Collections;
import java.util.Comparator;
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
 * Created 2/5/13 12:43 AM
 */
public class ActivePersistenceImpl implements ActivePersistence
{
    private static Session session = HibernateUtilities.getSessionFactory().openSession();


    private ActivePersistenceImpl()
    {

    }

    private static ActivePersistenceImpl instance;


    public static ActivePersistenceImpl getInstance()
    {
        if (null == instance) {
            instance = new ActivePersistenceImpl();
        }
        return instance;
    }


    public <FoundationalEntity extends Foundation> void create(FoundationalEntity foundation)
    {


        try {
            Transaction tx = session.beginTransaction();
            session.save(foundation);
            tx.commit();
        }
        catch (ClassCastException e) {
            e.printStackTrace();
        }
        catch (PropertyValueException e) {
            e.printStackTrace();
        }


    }


    public <FoundationalEntity extends Foundation> void createMany(List<FoundationalEntity> foundations)
    {


        int counter = 0;
        Transaction tx = session.beginTransaction();
        // loop over entities, and save them all.
        for (FoundationalEntity foundation : foundations) {
            try {
                if (foundation.getName() == null && !(foundation instanceof Label)) {
                    foundation.setName("anonymous");
                }
                else if ((foundation instanceof Label) && ((Label) foundation).getValue() == null) {
                    ((Label) foundation).setValue("anonymous");
                }
            }
            catch (NullPointerException e) {
                continue;
            }
            try {
                session.saveOrUpdate(foundation);
            }
            catch (Throwable e) {
                e.printStackTrace();
            }
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


    public <FoundationalEntity extends Foundation> FoundationalEntity read(String foundationId, Class<? extends FoundationalEntity> type)
    {


        //        Session session = HibernateUtilities.getSessionFactory().openSession();


        @SuppressWarnings("unchecked") FoundationalEntity entity = (FoundationalEntity) session.get(type,
                                                                                                    foundationId);

        //        session.close();

        return entity;
    }


    public <FoundationalEntity extends Foundation> FoundationalEntity update(FoundationalEntity foundation)
    {

        Transaction tx = session.beginTransaction();
        session.update(foundation);
        tx.commit();

        @SuppressWarnings("unchecked") FoundationalEntity entity = (FoundationalEntity) session.get(foundation.getClass(),
                                                                                                    foundation.getId());

        return entity;

    }


    public <FoundationalEntity extends Foundation> void delete(FoundationalEntity foundation)
    {

        Transaction tx = session.beginTransaction();
        session.delete(foundation);
        tx.commit();

    }

    public <FoundationalEntity extends Foundation> FoundationalEntity findByProperty(Class<? extends Foundation> type, String key, String value) {

        List<FoundationalEntity> foundations = session.createCriteria(type)
            .add(Restrictions.like(key,
                                   value))
            .list();

        try {
            return foundations.get(0);
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public <FoundationalEntity extends Foundation> FoundationalEntity findByProperty(Class<? extends Foundation> type, String key, Foundation value) {

        List<FoundationalEntity> foundations = session.createCriteria(type)

            .add(Restrictions.disjunction().add(Restrictions.eq(key+".id", value.getId())))
            .list();

        try {
            return foundations.get(0);
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }

//        return null;
    }

    public <FoundationalEntity extends Foundation> FoundationalEntity findByLabel(Class<FoundationalEntity> type, String value) {

        /**
         * NOTES
         *
         *
         *
         *
         *        SELECT * FROM pet WHERE name REGEXP 'Bob|Smith'
         *        SELECT * FROM tbl_name WHERE fieldname SOUNDS LIKE 'element'
         *
         *
         *
         *
         *
         *        select
         *              plant.id, label.value
         *          from
         *              tribe.Label label
         *        	left outer join tribe.PlantedPlant as plant
         *        	on plant.name = label.id
         *          where
         *              label.value LIKE '%hyssop%'
         *
         *
         *
         */
        SQLQuery query = session.createSQLQuery("select label.value from Label label left outer join "+type.getSimpleName()+" as parent on parent.name = label.id where label.value LIKE '%"+value+"%'");

        List<String> list = query.list();

        for (String name : (List<String>)query.list()) {
            System.out.println(soundex(name));
        }

        Collections.sort(list, new Comparator<String>()
        {
            public int compare(String o1, String o2)
            {
                return Integer.valueOf(soundex(o2)).compareTo(Integer.valueOf(soundex(o1)));
            }
        });



        query = session.createSQLQuery("select parent.id from Label label left outer join "+type.getSimpleName()+" as parent on parent.name = label.id where label.value LIKE '"+list.iterator().next()+"'");
        FoundationalEntity entity = null;
        for (String id : (List<String>)query.list()) {
            if (null != id) {
               entity = this.read(
                                (String)id, (Class<FoundationalEntity>)type);
                break;

            }
        }


        return entity;

//        query.setResultTransformer(Transformers.aliasToBean(type));
//        List<FoundationalEntity> entities = new ArrayList<FoundationalEntity>();
//        for (String asdf : (List<String>)query.list()) {
//
//            FoundationalEntity entity = this.read(
//                                                                            asdf, (Class<FoundationalEntity>)type);
//            entity.getName();
//            entities.add(entity);
//        }
//
//        return entities;




//
//        Label label = Label.create("SOUNDEX('" + value + "')");
//
//        List<FoundationalEntity> foundations = session.createCriteria(type)
//
//            .add(Restrictions.disjunction().add(Restrictions.like("name", label)))
//            .list();
//
//        try {
//            return foundations;
//        }
//        catch (IndexOutOfBoundsException e) {
//            return null;
//        }

//        return null;
    }

    public <FoundationalEntity extends Foundation> List<FoundationalEntity> findByLabel(String type, String value) {

        /**
         * NOTES
         *
         *
         *
         *
         *        SELECT * FROM pet WHERE name REGEXP 'Bob|Smith'
         *        SELECT * FROM tbl_name WHERE fieldname SOUNDS LIKE 'element'
         *
         *
         *
         *
         *
         *        select
         *              plant.id, label.value
         *          from
         *              tribe.Label label
         *        	left outer join tribe.PlantedPlant as plant
         *        	on plant.name = label.id
         *          where
         *              label.value LIKE '%hyssop%'
         *
         *
         *
         */


        return session.createSQLQuery("select * from Label label left outer join "+type+" as parent on parent.name = label.id where label.value LIKE '%"+value+"%'").list();

//
//        Label label = Label.create("SOUNDEX('" + value + "')");
//
//        List<FoundationalEntity> foundations = session.createCriteria(type)
//
//            .add(Restrictions.disjunction().add(Restrictions.like("name", label)))
//            .list();
//
//        try {
//            return foundations;
//        }
//        catch (IndexOutOfBoundsException e) {
//            return null;
//        }

//        return null;
    }

    public <FoundationalEntity extends Foundation> FoundationalEntity findByLabelId(Class<? extends Foundation> type, String id) {




        List<FoundationalEntity> foundations = session.createCriteria(type)

            .add(Restrictions.disjunction().add(Restrictions.eq("name.id", id)))
            .list();

        try {
            return foundations.get(0);
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }

//        return null;
    }











    public static String soundex(String s) {
        char[] x = s.toUpperCase().toCharArray();
        char firstLetter = x[0];

        // convert letters to numeric code
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {
                case 'B':
                case 'F':
                case 'P':
                case 'V': { x[i] = '1'; break; }

                case 'C':
                case 'G':
                case 'J':
                case 'K':
                case 'Q':
                case 'S':
                case 'X':
                case 'Z': { x[i] = '2'; break; }

                case 'D':
                case 'T': { x[i] = '3'; break; }

                case 'L': { x[i] = '4'; break; }

                case 'M':
                case 'N': { x[i] = '5'; break; }

                case 'R': { x[i] = '6'; break; }

                default:  { x[i] = '0'; break; }
            }
        }

        // remove duplicates
        String output = "" + "";//firstLetter;
        for (int i = 1; i < x.length; i++)
            if (x[i] != x[i-1] && x[i] != '0')
                output += x[i];

        // pad with 0's or truncate
        output = output + "0000";
        return output.substring(0, 4);
    }



}
