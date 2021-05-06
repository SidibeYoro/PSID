package com.project.givemehand.services;

import com.project.givemehand.interfaces.IDemande;
import com.project.givemehand.models.entity.*;
import com.project.givemehand.repository.RequestRepository;
import com.project.givemehand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
/**
 * Cette classe contient les services de la demande
 */
public class RequestService implements IDemande {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
            DateFormat.SHORT,
            DateFormat.SHORT);



    public List<Demande> getAllServiceRequest() {
        return  requestRepository.findAll();
    }

    public Demande getServiceRequest(long id) {
        return  requestRepository.getOne(id);
    }

    public Demande addRequestService(Demande demande)
    {
        return this.requestRepository.save(demande);
    }
    public Demande findDemandeById(long id) {
        return  requestRepository.findById(id).get();
    }
    public void deleteServiceRequest(Long requestId) {
        Demande deletedRequest=this.requestRepository.findById(requestId).get();
        boolean isPresent = this.requestRepository.findById(requestId).isPresent();
        if(isPresent) {
            this.requestRepository.deleteById(requestId);
        }
    }

    public Demande getServiceRequest(Long id)
    {
        Demande demande = this.requestRepository.findById(id).get();
        return demande;
    }

    public List<Demande> filterRequest(Filtre f) {
        List<Demande>  demandes = requestRepository.findAll();
        List<Demande> demandesRetenues= new ArrayList<Demande>();

        for (Demande req : demandes){
            String statRequest = req.getStatut();
            Statut  statutFilter = f.getStatut();
            Date date = req.getDateDemande();
            Date dateFilter = f.getDateFiltre();
            int medaillesReq = req.getOffre().getNbMedailles();
            int medaillesFilter = f.getMedailles();

            if( statRequest.toUpperCase().equals(statutFilter.toString().toUpperCase()) && ( medaillesReq == medaillesFilter)
                    && ( dateFilter.before(date)|| date.equals(dateFilter)) && (req.getUser().getId() == f.getIdUser()))
            {
               demandesRetenues.add(req);
            }
        }

        return demandesRetenues;
    }

    public ResponseEntity<Demande> updateRequestService(Long id, Demande demande) {
        Optional<Demande> requestServiceData = requestRepository.findById(id);

        if (requestServiceData .isPresent()) {
            Demande _request = requestServiceData .get();
            _request.setStatut(Statut.valueOf(demande.getStatut()));
            System.out.println("The request service is updated successfully !");
            return new ResponseEntity<>(requestRepository.save(_request), HttpStatus.OK);
        } else {
            System.out.println("Error, the request service is not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Demande getRequestServiceById(Long id_demande) {
        return this.requestRepository.findById(id_demande).get();
    }

    public List<Demande> getDemandesByOffre(Offre off) {
        List<Demande> allDemandes = requestRepository.findAll();
        List<Demande> demandesRetenues = new ArrayList<>();
        for (Demande d : allDemandes) {
            if(d.getOffre().equals(off)){
                demandesRetenues.add(d);
            }
        }
        return demandesRetenues;
    }

    public boolean isDemandCanUpdated(Long idDemande, String newStatut) {
        Demande d = requestRepository.findById(idDemande).get();

        if (d.getStatut().equals(Statut.ACCEPTE.toString())) {
            if (newStatut.equals(Statut.TERMINE.toString())) {
                return true;
            } else {
                return false;

            }
        }

       else if (d.getStatut().equals(Statut.REFUSE.toString())) {
            return false;

        }
       else if (d.getStatut().equals(Statut.ATTENTE.toString())) {
            if (newStatut.equals(Statut.TERMINE.toString())) {
                return false;
            } else {
                return true;
            }

        }
        else {
            return false;

        }

    }

    public Demande virtualMoney(Long idDemande){
        Demande d = requestRepository.findById(idDemande).get();

     //   int nbMedaillesTotalesOffreur=0;
       // int nbMedaillesTotalesDemandeur=0;

        Offre offre  = d.getOffre();
        int nbMedaillesOffre = offre.getNbMedailles();
        // Obtenir l'utilisateur a qui appartient l'offre
        User userOffreur = offre.getUser();
        int nbMedailleOffreur = userOffreur.getMedailles();
        // Nombre de medailles Totales de l'utilisateur a qui appartient l'offre
        int nbMedaillesTotalesOffreur = nbMedaillesOffre + nbMedailleOffreur;
        // Obtenir l'utilisateur qui fait la demande
        User userDemandeur = d.getUser();
        int nbMedailleDemandeur= userDemandeur.getMedailles();
        int nbMedaillesTotalesDemandeur = nbMedailleDemandeur - nbMedaillesOffre;


        if(d.getStatut().equals(Statut.ACCEPTE.toString())){
            userOffreur.setMedailles(nbMedaillesTotalesOffreur);
            userDemandeur.setMedailles(nbMedaillesTotalesDemandeur);
            requestRepository.save(d);
        }

        return d;

    }
  public Demande UpdateDemandeIsNoted(Long id)
  {
      Demande demande= requestRepository.findById(id).get();
      demande.setIs_noted(true);
      this.requestRepository.save(demande);

      return demande;
  }

    public boolean existByIdOffer(Long idoffre, Long iduser) {
        List<Demande> demandes = requestRepository.findAll();
        for (Demande d : demandes) {
            if((d.getOffre().getId() == idoffre) && (d.getUser().getId() == iduser)) {
                return true;
            }
        }
        return false;
    }

    public List<Demande> filterByStatut(Filtre f) {
        List<Demande>  demandes = requestRepository.findAll();
        List<Demande> demandesRetenues= new ArrayList<Demande>();
        for (Demande req : demandes){
            String statRequest = req.getStatut();
            Statut  statutFilter = f.getStatut();
            if( statRequest.toUpperCase().equals(statutFilter.toString().toUpperCase()) && (req.getUser().getId() == f.getIdUser()))
            {
                demandesRetenues.add(req);
            }
        }
        return demandesRetenues;
    }

    public List<Demande> filterByStatutAndnbMedailles(Filtre f) {
        List<Demande>  demandes = requestRepository.findAll();
        List<Demande> demandesRetenues= new ArrayList<Demande>();

        for (Demande req : demandes){
            String statRequest = req.getStatut();
            Statut  statutFilter = f.getStatut();
            int medaillesReq = req.getOffre().getNbMedailles();
            int medaillesFilter = f.getMedailles();

            if( statRequest.toUpperCase().equals(statutFilter.toString().toUpperCase()) && ( medaillesReq == medaillesFilter) && (req.getUser().getId() == f.getIdUser()))
            {
                demandesRetenues.add(req);

            }
        }
        return demandesRetenues;
    }

    public List<Demande> filterByStatutAndDate(Filtre f) {
        List<Demande>  demandes = requestRepository.findAll();
        List<Demande> demandesRetenues= new ArrayList<Demande>();

        for (Demande req : demandes){
            String statRequest = req.getStatut();
            Statut  statutFilter = f.getStatut();
            Date date = req.getDateDemande();
            Date dateFilter = f.getDateFiltre();
            System.out.println("La date :"+shortDateFormat.format(date).substring(0,11));
            System.out.println("Le df :"+shortDateFormat.format(dateFilter).substring(0,11));

            if( statRequest.toUpperCase().equals(statutFilter.toString().toUpperCase())  &&  (dateFilter.before(date) || shortDateFormat.format(date).substring(0,11).equals(shortDateFormat.format(dateFilter).substring(0,11))) && (req.getUser().getId() == f.getIdUser()))
            {
                demandesRetenues.add(req);

            }
        }

        return demandesRetenues;
    }
}
