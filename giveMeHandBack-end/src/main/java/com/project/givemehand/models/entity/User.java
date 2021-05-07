package com.project.givemehand.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cette classe represente la liste des utilisateurs ( particuliers )
 */
@Entity

@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class User {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    private int medailles;
    private Date dateInscription;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Adresse adresse;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Demande> demandes= new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Offre> offres= new HashSet<>();
    public User(){
    }

    public User( String email, String password) {
        this.password = password;
        this.email = email;
    }

    public User(String firstName, String lastName, String password, String email, String phoneNumber, int medailles, Date dateInscription) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.medailles = medailles;
        this.dateInscription=dateInscription;
    }



    public User( String firstName, String lastName, String password, String email, String phoneNumber, int medailles, Date dateInscription, Adresse adresse, Set<Demande> demandes, Set<Offre> offres) {
      //  this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.medailles = medailles;
        this.dateInscription = dateInscription;
        this.adresse = adresse;
        this.demandes = demandes;
        this.offres = offres;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMedailles() {
        return medailles;
    }

    public void setMedailles(int medailles) {
        this.medailles = medailles;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Set<Demande> getDemandes() {
        return demandes;
    }
    public Long getId () {
        return id;
    }
    public void setId (Long id){
        this.id = id;
    }

    public void setDemandes(Set<Demande> demandes) {
        this.demandes = demandes;
    }

    public Set<Offre> getOffres() {
        return offres;
    }

    public void setOffres(Set<Offre> offres) {
        this.offres = offres;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", medailles=" + medailles +
                ", dateInscription=" + dateInscription +
                ", roles=" + roles +
                ", adresse=" + adresse +
                ", demandes=" + demandes +
                ", offres=" + offres +
                '}';
    }
}