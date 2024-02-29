package com.example.todo.dao;

import com.example.todo.fakebd.NoteFakeRepository;
import com.example.todo.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteFakeRepository noteFakeRepository;

    @Override
    public List<Note> listAll() {
        List<Note> notes = new ArrayList<>();
        for(Map.Entry<Long, Note> entry : noteFakeRepository.getNotes().entrySet()){
            notes.add(entry.getValue());
        }
        return notes;
    }

    @Override
    public Note add(Note note) {
        noteFakeRepository.getNotes().put(note.getId(), note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        noteFakeRepository.getNotes().remove(id);
    }

    @Override
    public void update(Note note) {
       if(noteFakeRepository.getNotes().containsKey(note.getId())){
           Note n = getById(note.getId());
           n.setTitle(note.getTitle());
           n.setContent(note.getContent());
       }else{
           throw new RuntimeException();
       }

    }

    @Override
    public Note getById(long id) {
        return noteFakeRepository.getNotes().get(id);
    }
}
