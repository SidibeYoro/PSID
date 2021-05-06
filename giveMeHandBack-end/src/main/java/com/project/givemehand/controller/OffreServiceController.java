package com.project.givemehand.controller;
import com.project.givemehand.models.entity.*;
import com.project.givemehand.services.OffreService;
import com.project.givemehand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *  Elle represente le comtroller de la classe OffreService
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class OffreServiceController
{
    @Autowired
    private OffreService service;
    @Autowired
    private UserService userService;

    @RequestMapping(path ="/getAllOffer", method = RequestMethod.GET,produces="application/json")
    public List<Offre> getAlloffres()
    {

        return service.getAlloffres();
    }

    /*@RequestMapping(path ="/getAllOfferUser/{id", method = RequestMethod.GET)
    public List<Offre> getOfferByUser(@PathVariable long id)
    {
        return  service.getOfferByUser(id);
    }*/

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Offre findById(@PathVariable Long id) {

        return service.getOfferById(id);
    }

    /**
     * API renvoyant la liste des catégorie des offres
     * @return categories []
     */
    @RequestMapping(path ="/getAllCat", method = RequestMethod.GET)
    public Categorie[] getAllCat(){
        Categorie[] categories = Categorie.values();
        return categories;
     }

     /**
       * API filtrant les offres
      * @param categorie
      * @param ville
      * @param nbMedailles
      * @param motcle
      * @param date
      * @return offres :liste des offres filtrées
      */
    @GetMapping(value = "/filterOffers")
    public List<Offre> FilterOffre(@RequestParam("categorie") String categorie, @RequestParam("ville") String ville, @RequestParam("nbMedailles") String nbMedailles, @RequestParam("motcle") String motcle, @RequestParam("date") String date)
    {
        System.out.println("Catégorie"+categorie);
        Categorie cat = Categorie.valueOf(categorie);
        Filtre f;

        String jour = date.substring(0,2);
        String mois = date.substring(2,4);
        String annee = date.substring(4,8);
        String d = jour.concat("/"+mois+"/"+annee);
        System.out.println(d);

        f = new Filtre(cat,ville, new Date(d),Integer.parseInt(nbMedailles),motcle);
        return service.filtrerOffre(f);
    }

    //creer une offre
    @RequestMapping(value = "/CreateOffer/{id}", method = RequestMethod.POST)  //ok
    public void SaveOffre(@RequestBody Offre offres, @PathVariable Long id)
    {
        //User user =userService.findById(id)
        service.save(offres,id);
    }

    //creer une offre et userID
    @RequestMapping(value = "/CreateAllOffer", method = RequestMethod.POST)  //ok
    public void SaveALLOffre(@RequestBody Offre offres, User user)
    {
        service.saveALL(offres,user);
    }


   //Update_offer
    @PutMapping("/UpdateOffer")  //encours
    public ResponseEntity<Offre> update(@RequestBody Offre offre)
    {
        return ResponseEntity.ok(service.update(offre));
    }

    //Supprimer une offer par id //ok
    @DeleteMapping("/DeleteOffer/{offreid}")
    public void deleteBook(@PathVariable("offreid") long id)
    {
        service.deleteoffer(id);
    }
    /*
    @RequestMapping(value = "/OfferById/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Offre> getOfffertByUser(@PathVariable Long userid)
    {
        //User user = userService.findById(userid);
        return service.getOfferByUser(userid);
    }*/

    @RequestMapping(path ="/mesoffres/{id_user}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Offre> getOfferByUserId(@PathVariable Long id_user)
    {
        return service.getOfferByUserId(id_user);
    }

    @RequestMapping(path ="/IdByEmail/{email}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getIdByEmail(@PathVariable String email)
    {
        return service.getIdByEmail(email);
    }

    //obtenir offre par email
    @RequestMapping(path ="/test/{user_email}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Offre> getOffEmail(@PathVariable("user_email") String email)
    {
        return service.getOffreByEmail(email);
    }

    @RequestMapping(path ="/moyennenote/{id_offre}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public double GetMoyenneNote(@PathVariable("id_offre")Long id_offre)
    {
        return service.GetMoyenneNote(id_offre);
    }
    @PutMapping("/Updatemoyenne/{id_offre}/{moyenne}")
    public void UpdateMoyenne(@PathVariable("id_offre")Long id_offre, @PathVariable("moyenne") float moyenne  )
    {
        this.service.UpdateMoyenne(id_offre, moyenne);
    }

}