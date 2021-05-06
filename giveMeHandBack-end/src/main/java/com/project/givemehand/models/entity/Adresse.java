package com.project.givemehand.models.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * La classe addresse contient l'addresse des utilisateurs
 */


@Entity
@Table (name ="Adresse")
public class Adresse
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long adresse_id;
    private String street;
    private String zip;
    private  String city;
    private  String country;


    public Adresse(){

    }

    /**
     *
     * @param street
     * @param zip
     * @param city
     * @param country
     */
    public Adresse(String street, String zip, String city, String country) {
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Long getId() {
        return adresse_id;
    }

    public void setId(Long id) {
        this.adresse_id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }


   /* public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;

    }
    */
    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + adresse_id +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
