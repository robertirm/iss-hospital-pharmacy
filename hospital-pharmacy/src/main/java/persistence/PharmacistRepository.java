package persistence;

import model.Pharmacist;

public interface PharmacistRepository extends Repository<Integer, Pharmacist> {
    Pharmacist getPharmacist(String username, String password);
}
