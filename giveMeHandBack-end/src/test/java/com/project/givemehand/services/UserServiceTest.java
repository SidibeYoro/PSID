package com.project.givemehand.services;

import com.project.givemehand.models.entity.*;
import com.project.givemehand.repository.OffreRepository;
import com.project.givemehand.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userepository;

    //Injecter le mock
    @InjectMocks
    UserService userservice;


    private User user = new User("Yoro", "Sidibe", "1234", "sidib.yoro@yahoo.fr", null, 10, null, null
        , null, null);

    private Offre offre =new Offre("football", "sport", null,null,"Paris",5, Categorie.SPORT,null);
    private Demande demande = new Demande( new Date(),Statut.ACCEPTE,null,null,false);
    private Demande demande2 = new Demande( new Date(),Statut.REFUSE,null,null,false);
    private Demande demande3 = new Demande( new Date(),Statut.ATTENTE,null,null,false);



    @Test
    public void saveUser()
    {
        Mockito.when(userepository.save(user)).thenReturn(user);
        User u = userservice.saveUser(user);
        Assert.assertEquals(u,user);
    }

    @Test
    public void getOffersByUser()
    {
        user.setOffres(Sets.newSet(offre));
        Mockito.when(userepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(user));
        Set<Offre> offres= userservice.getOffersByUser(Long.valueOf(1));
        Assert.assertEquals(offres,user.getOffres());
    }

    @Test
    public void getNbAcceptedDemande()
    {    user.setDemandes(Sets.newSet(demande));
         Mockito.when(userepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(user));
         int test =userservice.getNbAcceptedDemande(Long.valueOf(1));
         Assert.assertEquals(test,1);
    }

    @Test
    public void getNbRefusedDemande()
    {
        user.setDemandes(Sets.newSet(demande2));
        Mockito.when(userepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(user));
        int test =userservice.getNbRefusedDemande(Long.valueOf(1));
        Assert.assertEquals(test,1);
    }

    @Test
    public void getNbWaitingDemande()
    {
        user.setDemandes(Sets.newSet(demande3));
        Mockito.when(userepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(user));
        int test =userservice.getNbWaitingDemande(Long.valueOf(1));
        Assert.assertEquals(test,1);
    }
}