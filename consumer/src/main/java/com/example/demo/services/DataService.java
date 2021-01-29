package com.example.demo.services;

import com.example.demo.models.Note;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataService {


    private final String baseUrl;
    private final RestTemplate restTemplate;


    public DataService(RestTemplateBuilder restTemplateBuilder, @Value("${baseUrl}") String baseurl) {
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseurl;
    }

    public List<Note> getNotes() {
        String url = baseUrl + "notes";
        return List.of(this.restTemplate.getForObject(url, Note[].class));
    }

    public Note saveNote(Note note) {
        String url = baseUrl + "/note";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // create a map for post parameters
        Map<String, Object> map = Map.of("title",note.getTitle(),"content",note.getContent());
        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        // send POST request
        ResponseEntity<Note> response = this.restTemplate.postForEntity(url, entity, Note.class);

        // check response status code
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public Integer countNotes() {
        String url = baseUrl + "notes/count";
        return Integer.parseInt(this.restTemplate.getForObject(url, String.class));
    }
}

