package services;

import model.Medicine;
import model.Pharmacist;
import model.User;
import persistence.MedicalPersonnelRepository;
import persistence.MedicineRepository;
import persistence.OrderRepository;
import persistence.PharmacistRepository;

import java.util.List;

public class Services {
    private final MedicalPersonnelRepository medicalPersonnelRepository;
    private final OrderRepository orderRepository;
    private final PharmacistRepository pharmacistRepository;
    private final MedicineRepository medicineRepository;

    public Services(MedicalPersonnelRepository medicalPersonnelRepository, OrderRepository orderRepository, PharmacistRepository pharmacistRepository, MedicineRepository medicineRepository) {
        this.medicalPersonnelRepository = medicalPersonnelRepository;
        this.orderRepository = orderRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.medicineRepository = medicineRepository;
    }

    public Pharmacist loginPharmacist(String username, String password){
        return pharmacistRepository.getPharmacist(username, password);
    }

    public void addMedicine(Medicine medicine){
        medicineRepository.add(medicine);
    }

    public void deleteMedicine(Integer id){
        medicineRepository.delete(id);
    }

    public List<Medicine> getAllMedicine(){
        return medicineRepository.getAll();
    }

}
