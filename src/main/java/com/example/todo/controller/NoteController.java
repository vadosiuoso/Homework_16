package com.example.todo.controller;


import com.example.todo.dao.NoteService;
import com.example.todo.model.Note;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@Validated
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/list")
    public ModelAndView noteList(){
        ModelAndView result = new ModelAndView("allNotes");
        List<Note> notes = noteService.listAll();
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
        System.out.println(note.getId());
        Note ne = noteService.add(note);
        System.out.println(ne.getId());
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
        System.out.println("edited note " + id);
        Note note = noteService.getById(id);
        ModelAndView result = new ModelAndView("addNote");
        result.addObject("note", note);
        return result;
    }

}
