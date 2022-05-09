package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "medical_personnel", schema = "public")
public class MedicalPersonnel extends User{
    private String department;

    public MedicalPersonnel() {}

    public MedicalPersonnel(String username, String password) {
        super(username, password);
        this.department = null;
    }

    public MedicalPersonnel(String username, String password,String department) {
        super(username, password);
        this.department = department;
    }

    @Override
    @Id
    @Column(name = "id_medical_personnel")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    @Column(name = "username")
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    @Column(name = "password")
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Column(name = "department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
