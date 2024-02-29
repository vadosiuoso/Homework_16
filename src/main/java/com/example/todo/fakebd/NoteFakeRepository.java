package com.example.todo.fakebd;

import com.example.todo.model.Note;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NoteFakeRepository {
    private Map<Long, Note> notes = new HashMap<>();

    public Map<Long,Note> getNotes() {
        return notes;
    }
}
