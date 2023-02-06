package com.example.demo.DemoService;

import com.example.demo.*;
import com.example.demo.models.Shapes;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class DemoService {
    private final ShapeManger manger;
    @Autowired
    public DemoService(ShapeManger manger ){
        this.manger=manger;
    }
    public String convert(String path){
        String le="";
        for(int i=0;i<path.length();i++){
            if(path.charAt(i)=='*')le+='/';
            else le+=path.charAt(i);
        }
        return le;
    }
    public boolean saveJson(String path) throws IOException {
        return SaveLoad.saveJSON(this.convert(path));
    }
    public String[] loadJson(String path) throws IOException, org.json.simple.parser.ParseException, CloneNotSupportedException {
        this.clear();
        return SaveLoad.loadJSON(this.convert(path));
    }
    public boolean saveXml(String path) throws IOException {
        return SaveLoad.saveXML(this.convert(path));
    }
    public String[] loadXml(String path) throws IOException, org.json.simple.parser.ParseException, CloneNotSupportedException {
        this.clear();
        return SaveLoad.loadXML(this.convert(path));
    }

    public  String create(String type, String json) throws JsonProcessingException, CloneNotSupportedException {
        Model.addElement(manger.createShape(type,json));
        undo_redo.add(manger.createShape(type,json));
        return manger.createShape(type,json).toJson();
    }

    public String update(String id,String json) throws JsonProcessingException, CloneNotSupportedException {
        Shapes shape=Model.getShape(id);
        undo_redo.add(manger.createShape(shape.getType(),shape.toJson()));
        shape.fromJson(json);
        Model.addElement(shape);
        return shape.toJson();
    }
    public void delete(String id){
        undo_redo.add(Model.getShape(id));
        Model.delete(id);
    }

    public String undo() throws JsonProcessingException {
        return undo_redo.undo();
    }

    public String redo() throws JsonProcessingException, CloneNotSupportedException {
        return undo_redo.redo();
    }

    public void clear(){
        Model.clear();
        undo_redo.st1.clear();
        undo_redo.st2.clear();
    }
    public HashMap<String,Shapes>  print(){
        return Model.getDataBase();
    }


}