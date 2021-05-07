package com.project.givemehand.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cette classe permet d'avoir les offres, une offre est reliee a un titre, description, ville, date de publication
 * et date de fin de l'offre
 */
@Entity
@Table ( name ="Offre")
public class Offre
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    //@JsonFormat(pattern="dd/MM/yyyy",timezone="Europe/France")
    private Date dateOffre;
    //@JsonFormat(pattern="dd/MM/yyyy",timezone="Europe/France")
    private Date dateFinOffre;
    private String villeOffre;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Note note;
    private float moyenneNotes;
    private int nbMedailles;
    private String categorie;
    @ManyToOne
    private User user;

    public Offre()
    {

    }

    /**
     *
     * @param titre titre de l'offre
     * @param description description de l'offre
     * @param dateOffre date de publication de l'offre
     * @param dateFin date de fin de l'offre
     * @param villeOffre ville ou l'offre est proposee
     * @param nbMedailles nombre de medailles a fournir pour cette offre
     * @param cat Categorie de l'offre
     * @param user Utilisateur qui as cree cette offre
     */
    public Offre( String titre, String description, Date dateOffre,Date dateFin, String villeOffre, int nbMedailles, Categorie cat, User user) {
        this.titre = titre;
        this.description = description;
        this.dateOffre = dateOffre;
        this.villeOffre = villeOffre;
        this.nbMedailles = nbMedailles;
        this.categorie = cat.toString();
        this.user = user;
        this.note = new Note();
        this.dateFinOffre = dateFin ;
    }

    public Date getDateFinOffre() {
        return dateFinOffre;
    }

    public Note getNote() {
        return note;
    }

    public void setDateFinOffre(Date dateFinOffre) {
        this.dateFinOffre = dateFinOffre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOffre() {
        return dateOffre;
    }

    public void setDateOffre(Date dateOffre) {
        this.dateOffre = dateOffre;
    }

    public String getVilleOffre() {
        return villeOffre;
    }

    public void setVilleOffre(String villeOffre) {
        this.villeOffre = villeOffre;
    }

    public double getMoyenneNotes() {
        return moyenneNotes;
    }

    public void setMoyenneNotes(float moyenneNotes) {
        this.moyenneNotes = moyenneNotes;
    }

    public int getNbMedailles() {
        return nbMedailles;
    }

    public void setNbMedailles(int nbMedailles) {
        this.nbMedailles = nbMedailles;
    }

    public String getCat() {
        return categorie;
    }

    public void setCat(Categorie cat) {
        this.categorie = cat.toString();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    /*
    public Set<Demande> getDemandes() {
        return demandes;
    }
    public void setDemandes(Set<Demande> demandes) {
        this.demandes = demandes;
    }
    public void addDemande(Demande demande){
        this.demandes.add(demande);
    }
    public void deleteDemande(Demande demande){
        this.demandes.remove(demande);
    }*/

    public void setOneNote(int note) {
        switch (note) {
            case 1:
                this.note.setNote1();
                break;
            case 2:
                this.note.setNote2();
                break;
            case 3:
                this.note.setNote3();
                break;
            case 4:
                this.note.setNote4();
                break;
            default:
                this.note.setNote5();
        }
    }
    public void setNote(Note note) {
        this.note = note;
    }

    /**
     * Permet de calculer la moyenne reliee a une offre
     * Elle contient 05 attributs de notes : Note 1 represente 1 etoiles, Note 2 represente 2 etoiles......
     * Lorsque un utilisateur donne 1 etoiles, l'attribut note 1 est incrementee de 1 , 2 etoiles elle est incrementee de 2
     * Pour avoir la moyenne on calcule d'abord la somme : 1 * valeur obtenue dans 1 + 2 * valeur obtenue dans 2......
     * On divise cette somme par le nombre de notes donnes
     * @return la moyenne
     */
    public float calculMoyenne()
    {
        float somme =  this.note.getNote1() + this.note.getNote2()*2 + this.note.getNote3()*3 +this.note.getNote4()*4 + this.note.getNote5()*5;
        float nbNotes = this.note.getNote1()+this.note.getNote2()+this.note.getNote3()+this.note.getNote4()+this.note.getNote5();
        this.moyenneNotes = somme/nbNotes;
        System.out.println("test moyenne"+this.moyenneNotes);
        return this.moyenneNotes;
    }

    /**
     * Accepte une demande
     * @param d represente la demande
     */
    public void acceptDemande(Demande d)
    {
        if (d.getStatut().equals(Statut.ATTENTE))
        {
            d.setStatut(Statut.ACCEPTE);
        }
    }

    /**
     * Refuser une demande
     * @param d represente la demande
     */
    public void refuserDemande(Demande d) {
        if (d.getStatut().equals(Statut.ATTENTE))
        {
            d.setStatut(Statut.REFUSE);
        }
    }
}