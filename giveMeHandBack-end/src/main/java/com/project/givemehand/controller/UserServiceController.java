package com.project.givemehand.controller;
import com.project.givemehand.models.entity.*;

import com.project.givemehand.payload.MessageResponse;
import com.project.givemehand.payload.request.LoginRequest;
import com.project.givemehand.payload.request.UserRequest;

import com.project.givemehand.repository.RoleRepository;
import com.project.givemehand.repository.UserRepository;
import com.project.givemehand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//import javax.validation.Valid;
import java.util.*;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.givemehand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth")

/**
 * Elle represente le controleur de la classe User Service
 */
public class UserServiceController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;
    Set<Role> roles = new HashSet<>();

    @Autowired
    RoleRepository roleRepository;
    
    @RequestMapping("/")
	public String index() {
		return "Welcome To GiveMeAHand";
	}

    @RequestMapping(value = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("signin"+loginRequest.getEmail());
        if (userRepository.existsByEmail(loginRequest.getEmail())==false) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email incorrecte !"));
        }
        else {
            User userDB = service.findByEmail(loginRequest.getEmail());
            String passWordDB =userDB.getPassword();
            byte[] decodedBytes = Base64.getDecoder().decode(passWordDB);
            String decodedPasswordDB = new String(decodedBytes);

                if (decodedPasswordDB.equals(loginRequest.getPassword())){
                    return ResponseEntity.ok(new MessageResponse("Connected"));
                }else {
                    return ResponseEntity.badRequest().body(new MessageResponse("Incorrect Password !"));
                }
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> userRegister(@RequestBody UserRequest userRequest ) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        int medailles = 0;
        Adresse add = new Adresse(userRequest.getStreet(),userRequest.getZip(), userRequest.getCity(), userRequest.getCountry());

        String password = userRequest.getPassword();
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        System.out.println("Password" + encodedPassword);
        // Date du jour
        Date inscriptionDate = new Date();
        User user = new User(userRequest.getFirstName(),userRequest.getLastName(),
                encodedPassword,userRequest.getEmail(),userRequest.getPhoneNumber(),medailles,inscriptionDate);

        System.out.println("********** User ************ " + "Firstname" + userRequest.getFirstName()

        + "Lastname" + userRequest.getLastName() + userRequest.getStreet() + userRequest.getCity() + userRequest.getZip()
                + userRequest.getCountry()
        );
        System.out.println("********** User ************ " + "Zip" + add.toString()

        );
              //  encoder.encode( userRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_PARTICULIER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));


        roles.add(userRole);
        user.setRoles(roles);
        user.setAdresse(add);
        user.setMedailles(10);
        service.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @RequestMapping(path ="/mesdemandes/{email}", method = RequestMethod.GET)
    public Set<Demande> findServiceRequestByEmail(@PathVariable String  email)
    {
        return service.findServiceRequestByEmail(email);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user)
    {
        return service.updateUser(user);
    }

    @RequestMapping(value = "/findUserByemail/{email}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findByEmail(@PathVariable ("email") String email ) {
        return service.findUserByEmail(email);
    }
  
    @RequestMapping(value = "/getMedaillesByemail/{email}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public int getMedaillesByemail(@PathVariable ("email") String email )  {
        return service.getMedaillesByemail(email);
    }
  
    @RequestMapping(value = "/getIdUserByemail/{email}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)

    public Long getIdUserByemail(@PathVariable ("email") String email )  {
        return service.getIdUserByemail(email);
    }

    @RequestMapping(value = "/finduserById/{user_id}", method = RequestMethod.GET)
    public User findById(@PathVariable Long user_id)
    {
        return service.findById(user_id);
    }

    @RequestMapping(path ="/getAllUser", method = RequestMethod.GET)
    public List<User> getAllUser()
    {
        return service.getAllUser();
    }
    @RequestMapping(path ="/getOffersByUser/{userid}", method = RequestMethod.GET)

    public Set<Offre> getOffersByUser(@PathVariable("userid") long id ) {
        return service.getOffersByUser(id);
    }
    @RequestMapping(path ="/getNbAcceptedDemande/{userid}", method = RequestMethod.GET)

    public int getNbAcceptedDemande(@PathVariable("userid") long userid ){
        return service.getNbAcceptedDemande(userid);
    }
    @RequestMapping(path ="/getNbRefusedDemande/{userid}", method = RequestMethod.GET)
    public int getNbRefusedDemande(@PathVariable("userid") long userid ){

        return service.getNbRefusedDemande(userid);
    }
    @RequestMapping(path ="/getNbWaitingDemande/{userid}", method = RequestMethod.GET)
        public int getNbWaitingDemande(@PathVariable("userid") long userid ){
        return service.getNbWaitingDemande(userid);

    }

            @DeleteMapping("/deleteUser/{userid}")
    public void deleteBUser(@PathVariable("userid") long id)
    {
        service.deleteuser(id);
    }

    @RequestMapping(value = "/findIdUserByMail/{email}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Long findIdUserByMail(@PathVariable ("email") String email )  {
        return service.findIdUserByMail(email);
    }

}


