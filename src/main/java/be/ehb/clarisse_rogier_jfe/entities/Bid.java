package be.ehb.clarisse_rogier_jfe.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private double price;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product productId;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person personId;

    public Bid() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return personId;
    }

    public void setPerson(Person person) {
        this.personId = person;
    }

    public Product getProduct() {
        return productId;
    }

    public void setProduct(Product product) {
        this.productId = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
