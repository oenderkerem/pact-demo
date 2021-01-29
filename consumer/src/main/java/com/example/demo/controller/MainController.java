package com.example.demo.controller;

import com.example.demo.models.Note;
import com.example.demo.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private DataService dataService;

    @GetMapping("")
    public String index(){
        return "";
    }

    @GetMapping("/notes")
    public List<Note> getNotes(){
        return dataService.getNotes();
    }

    @GetMapping("/count")
    public Integer countNotes(){
        return dataService.countNotes();
    }

    @PostMapping("/note")
    public Note postNote(@RequestBody Note note){
        return dataService.saveNote(note);
    }



}
