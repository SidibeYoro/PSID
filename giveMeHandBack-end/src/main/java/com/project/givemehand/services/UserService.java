package com.project.givemehand.services;


import com.project.givemehand.models.entity.*;
import com.project.givemehand.payload.MessageResponse;
import com.project.givemehand.payload.request.UserRequest;
import com.project.givemehand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

import java.util.Set;
import java.util.Optional;


/**
 * Contient les services du User
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;


    public Set<Demande> getServiceRequestByUserId(Long id_user) {
        Set<Demande> demandes =  this.userRepository.findById(id_user).get().getDemandes();
        return demandes;
    }


    public User saveUser(User user) {
        return userRepository.save(user);

    }
    public Set<Offre> getOffersByUser(Long id){
        User user = userRepository.findById(id).get();
        return user.getOffres();
    }
    public int getNbAcceptedDemande(Long id){
        User user = userRepository.findById(id).get();
        Set<Demande> demandes = user.getDemandes();
        int nbreDemandesAcceptes =0;

        for (Demande d : demandes) {
            if(d.getStatut().equals(Statut.ACCEPTE.toString()) ||d.getStatut().equals(Statut.TERMINE.toString()) ){
                nbreDemandesAcceptes++;
            }
        }
        return nbreDemandesAcceptes;


    }
    public int getNbRefusedDemande(Long id){
        User user = userRepository.findById(id).get();
        Set<Demande> demandes = user.getDemandes();
        int nbreDemandesRefuses =0;

        for (Demande d : demandes) {
            if(d.getStatut().equals(Statut.REFUSE.toString())){
                nbreDemandesRefuses++;
            }
        }
        return nbreDemandesRefuses;


    }
    public int getNbWaitingDemande(Long id){
        User user = userRepository.findById(id).get();
        Set<Demande> demandes = user.getDemandes();
        int nbreDemandesWaiting =0;

        for (Demande d : demandes) {
            if(d.getStatut().equals(Statut.ATTENTE.toString())){
                nbreDemandesWaiting++;
            }
        }
        return nbreDemandesWaiting;


    }
    /*public User updateUser(User user) {
        return userRepository.save(user);
    }
*/
    public User updateUser(User user) {
        user.setAdresse(user.getAdresse());
        return userRepository.save(user);


    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User findById(Long userID) {
        return userRepository.findById(userID).get();
    }
    public Long findIdUserByMail(String mail) {
        User u= userRepository.findByEmail(mail).get();
        return u.getId();
    }

    public ResponseEntity<User> findUserByEmail(String email) {
       User user  = userRepository.findByEmail(email).get();
        return ResponseEntity.ok().body(user);
    }

    public Set<Demande> findServiceRequestByEmail(String email) {
        Set<Demande> demandes =  this.userRepository.findByEmail(email).get().getDemandes();
        return demandes;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

        
        public User findUserById(Long id){

            return userRepository.findById(id).get();

        }

    public void deleteuser(long id) {
        this.userRepository.deleteById(id);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    public int getMedaillesByemail(String email) {
        User u = this.userRepository.findByEmail(email).get();
        return u.getMedailles();
    }

    public Long getIdUserByemail(String email) {
        User u = this.userRepository.findByEmail(email).get();
        return u.getId();
    }
}
