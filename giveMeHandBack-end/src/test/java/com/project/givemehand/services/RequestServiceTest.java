package com.project.givemehand.services;

import com.project.givemehand.models.entity.*;
import com.project.givemehand.repository.OffreRepository;
import com.project.givemehand.repository.RequestRepository;
import com.project.givemehand.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class RequestServiceTest {
    @Mock
    private RequestRepository resquesrepository;

   //injecter
    @InjectMocks
    RequestService requestservice;
   /* @BeforeEach
    public void init()
    {
        //Mockito.when(resquesrepository.save(demande)).thenReturn(demande);
        //Mockito.
    }*/

    private Demande demande = new Demande( new Date(),Statut.ATTENTE,null,null,false);
    //private Filtre filtre = new Filtre(Categorie.BRICOLAGE, "Paris", new Date(), 5, "brico");
    private  Filtre filtre= new Filtre(Statut.ACCEPTE, 5, new Date(), Long.valueOf(1));
    private Offre offre =new Offre("football", "sport", null,null,"Paris",5, Categorie.SPORT,null);
    @Test
    public void addRequestService()
    {
        Mockito.when(resquesrepository.save(demande)).thenReturn(demande);
       Demande test =requestservice.addRequestService(demande);
       Assert.assertEquals(test,demande);
    }

   @Test
    public void deleteServiceRequest()
   {
       Mockito.when(resquesrepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(demande));
       //Demande test = requestservice.deleteServiceRequest(Long.valueOf(1));
       requestservice.deleteServiceRequest(Long.valueOf(1));
   }

    @Test
    public void getServiceRequest()
    {
        Mockito.when(resquesrepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(demande));
        Demande test = requestservice.getServiceRequest(Long.valueOf(1));
        Assert.assertEquals(test,demande);
    }

    @Test
    public void filterRequest()
    {
        demande.setOffre(offre);
        List<Demande> demandes= Arrays.asList(demande);
        Mockito.when(resquesrepository.findAll()).thenReturn(demandes);
        List<Demande> test = requestservice.filterRequest(filtre);
        Assert.assertTrue(test.size()==0);
    }

    @Test
    public void updateRequestService()
    {
        Mockito.when(resquesrepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(demande));
        Mockito.when(resquesrepository.save(demande)).thenReturn(demande);
        ResponseEntity response =requestservice.updateRequestService(Long.valueOf(1),demande);
        Assert.assertTrue(response.getBody() instanceof Demande && response.getStatusCode()== HttpStatus.OK);

    }


}