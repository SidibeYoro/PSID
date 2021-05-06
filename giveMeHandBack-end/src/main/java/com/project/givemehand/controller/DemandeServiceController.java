package com.project.givemehand.controller;

import com.project.givemehand.models.entity.*;
import com.project.givemehand.repository.RequestRepository;
import com.project.givemehand.services.OffreService;
import com.project.givemehand.services.RequestService;
import com.project.givemehand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
/**
 *  Elle represente le controller de la classe demandeService
 */
public class DemandeServiceController {

    @Autowired
    private RequestService service;
    @Autowired
    private OffreService serviceOffre;
    @Autowired
    private UserService serviceUser;
    private RequestRepository demandesRep;


    @Autowired
    private OffreService offreService;
    @RequestMapping(path ="/requestServiceById/{id}", method = RequestMethod.GET)
    public Demande getServiceRequest( @PathVariable("id") Long id)
    {
        return service.getServiceRequest(id);
    }

    @RequestMapping(path ="/addRequestService/{mail}/{idoffre}", method = RequestMethod.POST)
    public Demande addDemande(@RequestBody Demande demande,@PathVariable Long idoffre, @PathVariable String mail)
    {
        User user = serviceUser.findByEmail(mail);
        if (!service.existByIdOffer(idoffre, user.getId())) {
            Offre offre = serviceOffre.getOfferById(idoffre);
            demande.setUser(user);
            demande.setOffre(offre);
            return service.addRequestService(demande);
        }
        return  null;
    }

    @DeleteMapping(value = "/deleteServiceRequest/{requestId}")
    public void deleteServiceRequest(@PathVariable("requestId") Long requestId)
    {
        service.deleteServiceRequest(requestId);
    }

    @RequestMapping(path ="/getRequestfilter/{sta}/{nbMedailles}/{date}/{iduser}", method = RequestMethod.GET)
    public List<Demande> getRequestService(@PathVariable String sta, @PathVariable String nbMedailles, @PathVariable String date, @PathVariable Long iduser)
    {
        Statut statut = Statut.valueOf(sta.toUpperCase());
        String mois = date.substring(0,2);
        String jour = date.substring(2,4);
        String annee = date.substring(4,8);
        String d = jour.concat("/"+mois+"/"+annee);
        Filtre f = new Filtre(statut,Integer.parseInt(nbMedailles), new Date(d), iduser);
        return service.filterRequest(f);
    }
    @RequestMapping(path ="/filterByStatut/{sta}/{iduser}", method = RequestMethod.GET)
    public List<Demande> filterByStatut(@PathVariable String sta, @PathVariable Long iduser)
    {
        Statut statut = Statut.valueOf(sta.toUpperCase());
        Filtre f = new Filtre(statut, iduser);
        return service.filterByStatut(f);
    }

    @RequestMapping(path ="/filterByStatutAndnbMedailles/{sta}/{nbMedailles}/{iduser}", method = RequestMethod.GET)
    public List<Demande> filterByStatutAndnbMedailles(@PathVariable String sta, @PathVariable String nbMedailles,@PathVariable Long iduser)
    {
        Statut statut = Statut.valueOf(sta.toUpperCase());
        Filtre f = new Filtre(statut,Integer.parseInt(nbMedailles), iduser);
        return service.filterByStatutAndnbMedailles(f);
    }

    @RequestMapping(path ="/filterByStatutAndDate/{sta}/{date}/{iduser}", method = RequestMethod.GET)
    public List<Demande> filterByStatutAndDate(@PathVariable String sta, @PathVariable String date,@PathVariable Long iduser)
    {
        Statut statut = Statut.valueOf(sta.toUpperCase());
        String mois = date.substring(0,2);
        String jour = date.substring(2,4);
        String annee = date.substring(4,8);
        String d = jour.concat("/"+mois+"/"+annee);
        System.out.println("d" + d);
        Filtre f = new Filtre(statut, new Date(d), iduser);
        System.out.println("new d" + new Date(d));
        return service.filterByStatutAndDate(f);
    }

    @RequestMapping(path ="/getDemandesByOffer/{idOffre}", method = RequestMethod.GET)
    public List<Demande> getDemandesByOffer(@PathVariable("idOffre") Long idOffre){
        System.out.println("Id Offre" + idOffre);

        Offre offre = offreService.getOfferById(idOffre);
        System.out.println("Offre" + offre.toString());

        return service.getDemandesByOffre(offre);

    }

    /**
     * API renvoyant la liste des catégorie des offres
     * @return Status []
     */
    @RequestMapping(path ="/getAllStatus", method = RequestMethod.GET)
    public Statut[] getAllStatus(){
        Statut[] statuts = Statut.values();
        return statuts ;
    }

    @RequestMapping(path ="/getAllDemandes", method = RequestMethod.GET)
    public List<Demande> getDemandesByOffer(){
        return demandesRep.findAll();
    }

    @RequestMapping(path="getNbDemandeByOffer", method = RequestMethod.GET)
    public Map<String, Integer> getNbDemandeByOffer(){
        Map<String, Integer> map = new HashMap<>();
        List<Offre> offres = this.offreService.getAlloffres();
        List<Demande> demandes;
        for ( Offre f : offres) {
            demandes = service.getDemandesByOffre(f);
            map.put(f.getTitre(),demandes.size());
        }
        return map;
    }
    //@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    //@RequestMapping(path="/incrementerMedailles/{idDemande}", method = RequestMethod.GET)
    @PutMapping("/virtualMoney/{idDemande}")
    public Demande virtualMoney(@PathVariable Long idDemande){
        return service.virtualMoney(idDemande);

    }
    @RequestMapping(path ="/isDemandCanUpdated/{idDemande}/{newStatut}", method = RequestMethod.GET)
    public boolean isDemandCanUpdated(@PathVariable Long idDemande, @PathVariable String newStatut){
            return service.isDemandCanUpdated(idDemande,newStatut);
    }


    @PutMapping("/updateRequestService/{id}")
    public ResponseEntity<Demande> updateRequestService(@PathVariable Long id, @RequestBody Demande demande)
    {
        return service.updateRequestService(id,demande);
    }

    @RequestMapping(value = "/getRequestServiceById/{id_demande}", method = RequestMethod.GET)
    public Demande getRequestServiceById(@PathVariable Long id_demande)
    {
        return service.getRequestServiceById(id_demande);
    }

    @PutMapping("/updateisnoted/{id_demande}")
     public Demande UpdateDemandeIsNoted(@PathVariable Long id_demande)
     {
         return service.UpdateDemandeIsNoted(id_demande);
     }
}
