package com.example.demo.controller;

import com.example.demo.DemoService.DemoService;
import com.example.demo.models.Shapes;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

@CrossOrigin()
@RestController
public class DemoController implements Serializable {

    DemoService service;
    @Autowired
    public DemoController(DemoService service){
        this.service=service;
    }
    @GetMapping("/create/{type}/{json}")
    public ResponseEntity<String> create(@PathVariable("type") String type, @PathVariable("json") String json) throws JsonProcessingException, CloneNotSupportedException {
        return new ResponseEntity<String>(service.create(type,json),HttpStatus.OK);
    }
    @GetMapping("/update/{id}/{json}")
    public ResponseEntity<String> update(@PathVariable("id") String id,@PathVariable("json") String json) throws JsonProcessingException, CloneNotSupportedException {
        return new ResponseEntity<String>(service.update(id,json),HttpStatus.OK);
    }


    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id){
        service.delete(id);
    }

    @GetMapping("/redo")
    public String  redo() throws JsonProcessingException, CloneNotSupportedException {
        String j= (service.redo());
        return j;
    }

    @GetMapping("/undo")
    public ResponseEntity<String> undo() throws JsonProcessingException {
        return new ResponseEntity<String>(service.undo(),HttpStatus.OK);
    }

    @GetMapping("/clear")
    public void clear(){
        service.clear();
    }

    @GetMapping("/print")
    public ResponseEntity<HashMap<String,Shapes>> print(){
        return new ResponseEntity<HashMap<String,Shapes>>(service.print(), HttpStatus.OK);
    }
    @GetMapping("/save/{path}")
    public ResponseEntity<Boolean> saveJson(@PathVariable("path") String path) throws IOException {
        service.saveXml(path+".xml");
        return new ResponseEntity<Boolean>(service.saveJson(path+".json"),HttpStatus.OK);
    }
    @GetMapping("/loadJson/{path}")
    public ResponseEntity<String[]> loadJson(@PathVariable("path") String path) throws IOException, org.json.simple.parser.ParseException, CloneNotSupportedException {
        return new ResponseEntity<String[]>(service.loadJson(path),HttpStatus.OK);
    }
    @GetMapping("/loadXML/{path}")
    public ResponseEntity<String[]> loadXml(@PathVariable("path") String path) throws IOException, org.json.simple.parser.ParseException, CloneNotSupportedException {
        return new ResponseEntity<String[]>(service.loadXml(path),HttpStatus.OK);
    }


}