package persistence.impl;

import model.Pharmacist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import persistence.PharmacistRepository;

import java.util.List;

public class PharmacistRepositoryImpl implements PharmacistRepository {
    private final SessionFactory sessionFactory;

    public PharmacistRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Pharmacist getPharmacist(String username, String password) {
        Pharmacist pharmacist = null;
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                pharmacist = session.createQuery("from Pharmacist where username='"+username+"' and password='"+password+"'",Pharmacist.class)
                        .setMaxResults(1)
                        .uniqueResult();

                tx.commit();
            }catch (RuntimeException ex){
                System.err.println("Error in getPharmacist " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return pharmacist;
    }

    @Override
    public Pharmacist getOne(Integer integer) {
        Pharmacist pharmacist = null;
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                pharmacist = session.createQuery("from Pharmacist where id="+integer,Pharmacist .class)
                        .setMaxResults(1)
                        .uniqueResult();

                tx.commit();
            }catch (RuntimeException ex){
                System.err.println("Error in getOne " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return pharmacist;
    }

    @Override
    public List<Pharmacist> getAll() {
        List<Pharmacist> pharmacists = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                pharmacists = session.createQuery("from Pharmacist ", Pharmacist.class).list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error in getAll " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return pharmacists;
    }

    @Override
    public void add(Pharmacist entity) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error in add " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void delete(Integer integer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Pharmacist crit = session.createQuery("from Pharmacist where id =" + integer, Pharmacist.class)
                        .setMaxResults(1)
                        .uniqueResult();
                System.err.println("Stergem utilizatorul " + crit.getId());
                session.delete(crit);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la stergere " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void update(Pharmacist entity) {

    }


}
