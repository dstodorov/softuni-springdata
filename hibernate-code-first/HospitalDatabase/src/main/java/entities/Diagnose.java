package entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "comments")
    private String comment;

    @OneToMany(targetEntity = Patient.class, mappedBy = "diagnose")
    @Column(nullable = false)
    private Set<Patient> patients;

    @OneToMany(targetEntity = Medication.class, mappedBy = "diagnose")
    @Column(nullable = false)
    private Set<Medication> medications;

    public Diagnose() {
    }

    public Diagnose(String name, String comment) {
        this.name = name;
        this.comment = comment;
        this.medications = medications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }
}
