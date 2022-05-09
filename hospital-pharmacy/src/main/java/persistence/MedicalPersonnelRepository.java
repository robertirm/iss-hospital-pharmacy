package persistence;

import model.MedicalPersonnel;


public interface MedicalPersonnelRepository extends Repository<Integer, MedicalPersonnel>{
    MedicalPersonnel getMedicalPersonnel(String username, String password);

}
