package com.example.todo.service;

import com.example.todo.model.Note;
import com.example.todo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoteService {
    private NoteRepository noteRepository;

    public NoteService(@Autowired NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll(){
        return noteRepository.findAll();
    }


    public Note add(Note note){
        return noteRepository.save(note);

    }
    public void deleteById(long id){
        noteRepository.deleteById(id);
    }

    public Note getById(long id){
        return noteRepository.getReferenceById(id);
    }

}
