package be.ehb.clarisse_rogier_jfe.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties("products")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @OneToMany(mappedBy = "owner")
    private List<Product> assortment = new ArrayList<>();

    public Person() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getAssortment() {
        return assortment;
    }

    public void setAssortment(List<Product> assortment) {
        this.assortment = assortment;
    }
}
