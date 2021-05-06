package com.project.givemehand.controller;

import com.project.givemehand.models.entity.Note;
import com.project.givemehand.models.entity.Offre;
import com.project.givemehand.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class NoteServiceController
{
    @Autowired
    private NoteService noteService;
    @RequestMapping(value = "/FindNoteById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Note findById(@PathVariable Long id)
    {

        return noteService.getNoteById(id);
    }
    @PutMapping("/SaveNote/{note}/{id}")
    private Offre saveNote(@PathVariable int note,@PathVariable Long id)
    {
        return noteService.saveNote(note,id);
    }
}
