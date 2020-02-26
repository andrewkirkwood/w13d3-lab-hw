package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public List<Distillery> findDistilleriesByRegion(String region) {
        List<Distillery> result = null;
        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr =  session.createCriteria(Distillery.class);
            cr.add(Restrictions.eq("region", region));
            result =  cr.list();
        }
        catch(HibernateException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Transactional
    public List<Whisky> findAllDistilleriesWhichHaveWhiskeysByYear(int year){
        List<Whisky> result = null;
        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr = session.createCriteria(Whisky.class);

            cr.createAlias("whisky", "distilleryAlias");
            cr.add(Restrictions.eq("distilleryAlias.region", region));
            result =  cr.list();
        }
        catch(HibernateException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
