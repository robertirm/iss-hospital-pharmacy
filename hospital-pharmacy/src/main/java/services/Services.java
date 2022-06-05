package services;

import model.*;
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

    public MedicalPersonnel loginMedicalPersonnel(String username,String password){
        return medicalPersonnelRepository.getMedicalPersonnel(username, password);
    }

    public void registerPharmacist(Pharmacist pharmacist) {
        pharmacistRepository.add(pharmacist);
    }

    public void registerMedicalPersonnel(MedicalPersonnel medicalPersonnel) {
        medicalPersonnelRepository.add(medicalPersonnel);
    }

    public void addMedicine(Medicine medicine){
        medicineRepository.add(medicine);
    }

    public void deleteMedicine(Integer id){
        medicineRepository.delete(id);
    }

    public void updateMedicine(Medicine medicine) {
        medicineRepository.update(medicine);
    }

    public List<Medicine> getAllMedicine(){
        return medicineRepository.getAll();
    }

    public void addOrder(Order order){
        orderRepository.add(order);
    }

    public void deleteOrder(Integer id){
        orderRepository.delete(id);
    }

    public void updateOrder(Order order) {orderRepository.update(order);}

    public List<Order> getAllOrders(){
        return orderRepository.getAll();
    }

}
