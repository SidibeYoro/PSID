package com.project.givemehand.services;

import com.project.givemehand.interfaces.IOffre;
import com.project.givemehand.models.entity.*;
import com.project.givemehand.repository.OffreRepository;
import com.project.givemehand.repository.RequestRepository;
import com.project.givemehand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Cette classe contient les services de l'offre
 */
@Service
public class OffreService implements IOffre {

    @Autowired
    private OffreRepository offreRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RequestService requestService;
    @Autowired
    private UserService userService;

    /**
     * Permet de filtrer les offres avec les parametres recus
     * @param f represente les parametres du filtre

     * @return une liste d'offre
     */
    public List<Offre> filtrerOffre(Filtre f) {

        List<Offre> offres = offreRepository.findAll();
        List<Offre> offresRetenues = new ArrayList<Offre>();
            Categorie catOffreFilter = f.getCategorie();
            Date dateFiltre = f.getDateFiltre();
            Date date = new Date("01/01/1000");
            String villeRecherche = f.getVille();
            int medaillesFiltre = f.getMedailles();
            System.out.println("If CAT" + !catOffreFilter.equals("TOUTES")+" Catégorie: "+catOffreFilter);
            String motClesFiltre = f.getMotCles();

            if (!catOffreFilter.equals(Categorie.TOUTES)) {
                offresRetenues = this.filterParCat(f);
            }
            else{
                offresRetenues = offres;
            }
            if (!villeRecherche.equals("")) {
                offresRetenues = this.filtrerParVille(f, offresRetenues);
            }
            System.out.println(("La date :"+dateFiltre));
            if (!dateFiltre.equals(date)) {
                offresRetenues = this.filtrerParDate(f, offresRetenues);
            }

            if (!motClesFiltre.equals("")) {
                offresRetenues = this.filtrerParMot(f, offresRetenues);
            }
            if (medaillesFiltre != (-1)) {
                offresRetenues = this.filterParMedailles(f, offresRetenues);
            }

        return offresRetenues;
    }

    /**
     * Renvoie la liste des offres filrées par catégorie
     * @param f filte
     * @return la liste d'offres de catégorie cat du filtre
     */
    public List<Offre> filterParCat(Filtre f){
        List<Offre> offres = offreRepository.findAll();
        List<Offre> offresRetenues = new ArrayList<Offre>();
        for (Offre off : offres) {
            String catOffre = off.getCat();
            Categorie catOffreFilter = f.getCategorie();

            if (catOffre.toUpperCase().equals(catOffreFilter.toString().toUpperCase())){
                offresRetenues.add(off);
            }
        }
        return offresRetenues;
    }

    /**
     * Renvoie la liste des offres filrées par ville
     * @param f filte
     * @param offres liste des offre à filtrer
     * @return la liste d'offres du filtre
     */
    public List<Offre> filtrerParVille(Filtre f, List<Offre> offres){
        List<Offre> offresRetenues = new ArrayList<Offre>();
        for (Offre off : offres) {
            if(off.getVilleOffre().toUpperCase().equals(f.getVille().toUpperCase())){
                offresRetenues.add(off);
            }
        }
        return  offresRetenues;
    }

    /**
     * Renvoie la liste des offres filrées par nombre de médailles
     * @param f filte
     * @param offres liste des offre à filtrer
     * @return la liste d'offres du filtre
     */
    public List<Offre> filterParMedailles(Filtre f,List<Offre> offres){
        List<Offre> offresRetenues = new ArrayList<Offre>();
        for (Offre off : offres) {
            if (off.getNbMedailles() <= f.getMedailles()) {
                offresRetenues.add(off);
            }
        }
        return offresRetenues;
    }

    /**
     * Renvoie la liste des offres filrées par date
     * @param f filte
     * @param offres liste des offre à filtrer
     * @return la liste d'offres du filtre
     */
    public List<Offre> filtrerParDate(Filtre f,List<Offre> offres){
        List<Offre> offresRetenues = new ArrayList<Offre>();
        for (Offre off : offres) {
            if (off.getDateFinOffre().equals(f.getDateFiltre()) || off.getDateFinOffre().before(f.getDateFiltre()) ) {
                offresRetenues.add(off);
            }
        }
        return offresRetenues;

    }
    /**
     * Renvoie la liste des offres filrées par mot cle
     * @param f filte
     * @param offres liste des offre à filtrer
     * @return la liste d'offres du filtre
     */
    public List<Offre> filtrerParMot(Filtre f,List<Offre> offres){
        List<Offre> offresRetenues = new ArrayList<Offre>();
        for (Offre off : offres) {
            if ((off.getDescription().toUpperCase().contains(f.getMotCles().toUpperCase())) || (off.getTitre().toUpperCase().contains(f.getMotCles().toUpperCase())) ) {
                offresRetenues.add(off);
            }
        }
        return offresRetenues;
    }

    /**
     * Renvoie la liste des offres sans filtre
     * @return la liste d'offres existants dans la base
     */
    public List<Offre> getAlloffres() {
        return offreRepository.findAll();
    }

    public List<Offre> getAlloffresbyuser(User user) {
        Optional<User> user1=userRepository.findById(user.getId());
        return offreRepository.findAll();
    }

    public Offre getOfferById(long id)
    {
        return this.offreRepository.findById(id).get();
    }

    public void save(Offre offres,Long id)
    {
        User user =userRepository.findById(id).get();
        offres.setUser(user);
        Note note =new Note();
        System.out.println(note.toString());
        offres.setNote(note);
        this.offreRepository.save(offres);
    }

    public void saveALL(Offre offres, User user)
    {   //offres.setUser(user)
        offres.setUser(user);
        //Note note =new Note();
       // System.out.println(note.toString());
        //offres.setNote(note);
        this.offreRepository.save(offres);
    }

    public void deleteoffer(long id) {
        Offre  f = offreRepository.findById(id).get();
        List <Demande> demandes = requestService.getDemandesByOffre(f);
        if(demandes.size()!=0) {
            for (Demande d : demandes) {
                requestService.deleteServiceRequest(d.getId());
            }
            this.offreRepository.deleteById(id);
        }else{
            this.offreRepository.deleteById(id);
        }

    }

    public Offre update(Offre offres) {
        //Optional<Offre> offreg = offreRepository.findById(id);
        return offreRepository.save(offres);


    }

    public Set<Offre> getOfferByUserId(Long id_user)
    {
        User user = this.userRepository.findById(id_user).get();
        Set<Offre> offres =  user.getOffres();
        return offres;
    }

    public Long getIdByEmail(String email)
    {
        User user =userRepository.findByEmail(email).get();
        Long id =user.getId();
        return id;
    }

    public Set<Offre> getOffreByEmail(String email)
    {
        User user =this.userRepository.findByEmail(email).get();
        Set<Offre> offre=user.getOffres();
        return offre;
    }

    public double GetMoyenneNote(Long id)
    {
        Offre offre = this.getOfferById(id);
        double moyenote=offre.calculMoyenne();
        return moyenote;
    }

    public void UpdateMoyenne(Long id, float moyenne)
    {
        Offre offre = this.getOfferById(id);
        offre.setMoyenneNotes(moyenne);
        this.offreRepository.save(offre);
    }
}



