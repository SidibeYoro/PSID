package com.project.givemehand.models.entity;


/**
 *
 * La classe enum categorie contient les differentes categories des offres des particulierss
 */
public enum Categorie {
    TOUTES("Toutes"),BRICOLAGE("Bricolage"), JARDINAGE("Jardinage"), GARDERIE("Garderie"), SPORT("Sport"),
    INFORMATIQUE("Informatique"), COURS_PARTICULIERS ("Cours_Particuliers") , DECORATION ("Décoration"),
    CUISINE("cuisine"), MENAGE("Ménage"), AUTRES("Autres");

    private String label;

    /**
     *
     * @param lab represente la categorie de service propose
     */
    private Categorie(String lab)
    {
        this.label = lab;
    }

}
