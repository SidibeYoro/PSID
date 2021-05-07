package com.project.givemehand.services;

import com.project.givemehand.models.entity.*;
import com.project.givemehand.repository.OffreRepository;
import com.project.givemehand.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class OffreServiceTest {
    @Mock
    OffreRepository offreRepository;


    @Mock
    UserRepository userrepository;

    //Injecter le mock
    @InjectMocks
    OffreService offreservice;
    private Offre offre =new Offre("football", "sport", null,null,"Paris",5, Categorie.SPORT,null);

    @Test
    public void getAlloffres()
    {
        List<Offre> offres= Arrays.asList(offre);
        Mockito.when(offreRepository.findAll()).thenReturn(offres);
        List<Offre> resultat=offreservice.getAlloffres();
        Assert.assertEquals(resultat,offres);
    }

    @Test
    public void save()
    {
        User user = new User("Yoro", "Sidibe", "1234", "sidib.yoro@yahoo.fr", null, 10, null, null
        , null, null);
        Mockito.when(userrepository.findById(Long.valueOf(1))).thenReturn(Optional.of(user));
        //Mockito.when(offreRepository.save(offre)).
           offreservice.save(offre,Long.valueOf(1));
    }

    @Test
    public void getMoyenneNote()
    {
        Note note = new Note(4,5,6,7,8);
        offre.setNote(note);
        Mockito.when(offreRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(this.offre));
        //Mockito.when(offre.calculMoyenne()).thenReturn(Float.valueOf(4));
        Long id= Long.valueOf(1);
        Double resultat=offreservice.GetMoyenneNote(id);
        Assert.assertTrue(resultat==offre.getMoyenneNotes());
    }

    @Test
    public void updateMoyenne()
    {
        Mockito.when(offreRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(offre));
        offreservice.UpdateMoyenne(Long.valueOf(1),Float.valueOf(1));
    }

    @Test
    public void update()
    {
        Mockito.when(offreRepository.save(offre)).thenReturn(offre);
        Offre resultat= offreservice.update(offre);
        Assert.assertEquals(resultat,offre);
    }



}