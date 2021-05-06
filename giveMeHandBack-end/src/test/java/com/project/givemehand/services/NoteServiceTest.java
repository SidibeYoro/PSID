package com.project.givemehand.services;

import com.project.givemehand.models.entity.Categorie;
import com.project.givemehand.models.entity.Note;
import com.project.givemehand.models.entity.Offre;
import com.project.givemehand.repository.NoteRepository;
import com.project.givemehand.repository.OffreRepository;
import com.project.givemehand.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {
    @Mock
    OffreRepository offreRepository;

   @Mock
    NoteRepository noterepository;
    @Mock
    UserRepository userrepository;
    @Mock
    OffreService offreservice;
    private Offre offre =new Offre("football", "sport", null,null,"Paris",5, Categorie.SPORT,null);
    private Note note = new Note(4,5,6,7,8);
    //Injecter le mock
    @InjectMocks
    NoteService noteservice;


    @Test
    public void getNoteById()
    {

        Mockito.when(noterepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(note));
        Note test = noteservice.getNoteById(Long.valueOf(1));
        Assert.assertEquals(test,note);
    }

    @Test
    public void saveNote()
    {

        Mockito.when(offreservice.getOfferById(Long.valueOf(1))).thenReturn((offre));
        Offre test= noteservice.saveNote(1,Long.valueOf(1));
        Assert.assertTrue(test.getNote().getNote1()>0 && test.getNote().getNote1()<6 );

    }
}