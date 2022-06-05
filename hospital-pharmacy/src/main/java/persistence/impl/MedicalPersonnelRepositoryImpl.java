package persistence.impl;

import model.MedicalPersonnel;
import model.Pharmacist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import persistence.MedicalPersonnelRepository;

import java.util.List;

public class MedicalPersonnelRepositoryImpl implements MedicalPersonnelRepository {
    private final SessionFactory sessionFactory;

    public MedicalPersonnelRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MedicalPersonnel getMedicalPersonnel(String username, String password) {
        MedicalPersonnel medicalPersonnel = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                medicalPersonnel = session.createQuery("from MedicalPersonnel where username='"+username+"' and password='"+password+"'",MedicalPersonnel.class)
                        .setMaxResults(1).uniqueResult();
                tx.commit();
            }catch (RuntimeException ex){
                System.err.println("Error in getPharmacist " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return medicalPersonnel;
    }

    @Override
    public MedicalPersonnel getOne(Integer integer) {
        MedicalPersonnel medicalPersonnel = null;
        try (Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                medicalPersonnel = session.createQuery("from MedicalPersonnel where id ="+integer,MedicalPersonnel.class)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
            }catch (RuntimeException ex) {
                System.err.println("Error in getOne " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return medicalPersonnel;
    }

    @Override
    public List<MedicalPersonnel> getAll() {
        List<MedicalPersonnel> medicalPersonnels = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                medicalPersonnels = session.createQuery("from MedicalPersonnel ", MedicalPersonnel.class).list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error in getAll " + ex);
                if(tx != null)
                    tx.rollback();
            }
        }
        return medicalPersonnels;
    }

    @Override
    public void add(MedicalPersonnel entity) {
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
                MedicalPersonnel crit = session.createQuery("from MedicalPersonnel where id =" + integer,MedicalPersonnel.class)
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
    public void update(MedicalPersonnel entity) {

    }
}
