package persistence.impl;

import model.MedicalPersonnel;
import org.hibernate.SessionFactory;
import persistence.MedicalPersonnelRepository;

import java.util.List;

public class MedicalPersonnelRepositoryImpl implements MedicalPersonnelRepository {
    private final SessionFactory sessionFactory;

    public MedicalPersonnelRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MedicalPersonnel getMedicalPersonnel(String username, String password) {
        return null;
    }

    @Override
    public MedicalPersonnel getOne(Integer integer) {
        return null;
    }

    @Override
    public List<MedicalPersonnel> getAll() {
        return null;
    }

    @Override
    public void add(MedicalPersonnel entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(MedicalPersonnel entity) {

    }
}
