package com.example.todo.controller;


import com.example.todo.model.Note;
import com.example.todo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@Validated
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;


    public NoteController (@Autowired NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public ModelAndView noteList(){
        ModelAndView result = new ModelAndView("allNotes");
        List<Note> notes = noteService.findAll();
        result.addObject("notes", notes);
        return result;
    }

    @GetMapping("/add")
    public ModelAndView addNote(){
        ModelAndView modelAndView = new ModelAndView("addNote");
        Note note = new Note();
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveNote(@ModelAttribute("note") Note note){
        Note ne = noteService.add(note);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        return new ModelAndView(redirectView);
    }

    @PostMapping("/delete")
    public ModelAndView deleteNote(@RequestParam("id") Long id){
        noteService.deleteById(id);
        return noteList();
    }

    @GetMapping("/edit")
    public ModelAndView editNote(@RequestParam("id") Long id){
        Note note = noteService.getById(id);
        ModelAndView result = new ModelAndView("addNote");
        result.addObject("note", note);
        return result;
    }

}
