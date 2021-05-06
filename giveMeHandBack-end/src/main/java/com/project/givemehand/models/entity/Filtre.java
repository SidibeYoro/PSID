package com.project.givemehand.models.entity;

import java.util.Date;

/**
 * La classe filtre contient les elements pour filtrer les offres, permet d'envoyer a l'utilisateur les meilleurs
 * resultatss
 */
public class Filtre  {

    Categorie categorie;
    String ville;
    Date dateFiltre;
    int medailles;
    String motCles;
    Statut statut;
    Long idUser;

    /**
     *
     * @param categorie d'offre qu'on veut filtrer
     * @param ville ou on souhaite rechercher l'offre
     * @param date pour l'offre
     * @param medailles : cout de l'offre
     * @param motCles : pour la recherche
     */
    public Filtre(Categorie categorie, String ville, Date date, int medailles, String motCles) {
        this.categorie = categorie;
        this.ville = ville;
        this.dateFiltre = date;
        this.medailles = medailles;
        this.motCles = motCles;
    }

    public Filtre(Statut statut, int medaille, Date date, Long idUser) {
        this.statut =statut;
        this.medailles =medaille;
        this.dateFiltre =date;
        this.idUser = idUser;

    }
    public Filtre(Statut statut, Long iduser) {
        this.statut =statut;
        this.idUser =iduser;
    }

    public Filtre(Statut statut, int medaille, Long iduser) {
        this.statut =statut;
        this.idUser =iduser;
        this.medailles =medaille;
    }

    public Filtre(Statut statut, Date d, Long iduser) {
        this.statut = statut;
        this.dateFiltre = d;
        this.idUser=iduser;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Date getDateFiltre() {
        return dateFiltre;
    }

    public void setDateFiltre(Date dateFiltre) {
        this.dateFiltre = dateFiltre;
    }

    public int getMedailles() {
        return medailles;
    }

    public void setMedailles(int medailles) {
        this.medailles = medailles;
    }

    public String getMotCles() {
        return motCles;
    }

    public void setMotCles(String motCles) {
        this.motCles = motCles;
    }
    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }


}
