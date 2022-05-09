package persistence.impl;

import model.Medicine;
import model.Pharmacist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import persistence.MedicineRepository;

import java.util.List;

public class MedicineRepositoryImpl implements MedicineRepository {
    private final SessionFactory sessionFactory;

    public MedicineRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Medicine getOne(Integer integer) {
        Medicine medicine = null;
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                medicine = session.createQuery("from Medicine where id="+integer,Medicine .class)
                        .setMaxResults(1)
                        .uniqueResult();

                tx.commit();
            }catch (RuntimeException ex){
                System.err.println("Error in getOne " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return medicine;
    }

    @Override
    public List<Medicine> getAll() {
        List<Medicine> medicines = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                medicines = session.createQuery("from Medicine ", Medicine.class).list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error in getAll " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return medicines;
    }

    @Override
    public void add(Medicine entity) {
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
                Medicine crit = session.createQuery("from Medicine where id =" + integer, Medicine.class)
                        .setMaxResults(1)
                        .uniqueResult();
                session.delete(crit);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error in delete " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void update(Medicine entity) {

    }
}
