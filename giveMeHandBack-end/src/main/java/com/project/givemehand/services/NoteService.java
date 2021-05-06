package com.project.givemehand.services;

import com.project.givemehand.models.entity.Note;
import com.project.givemehand.models.entity.Offre;
import com.project.givemehand.models.entity.User;
import com.project.givemehand.repository.NoteRepository;
import com.project.givemehand.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService
{
    @Autowired
   private  NoteRepository noteRepository;
    @Autowired
    private OffreService offreService;
    @Autowired
    private OffreRepository offreRepository;

    public Note getNoteById(long id)
    {
        return this.noteRepository.findById(id).get();
    }

    public Offre saveNote(int note, Long id)
    {
        Offre offre=offreService.getOfferById(id);
        offre.setOneNote(note);
        offreRepository.save(offre);
        return offre;
    }
}
