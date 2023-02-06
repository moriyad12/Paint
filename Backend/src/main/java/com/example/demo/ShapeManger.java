package com.example.demo;

import com.example.demo.models.Shapes;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class ShapeManger {
    private HashMap<String, Shapes>map=new HashMap<String,Shapes>();
    private ShapeFactory factory =new ShapeFactory();
    public Shapes createShape(String type, String json) throws CloneNotSupportedException, JsonProcessingException {
        Shapes shape=map.get(type);
        if(shape==null){
            map.put(type,factory.createShape(type));
        }
        shape= (Shapes) map.get(type).clone();
        shape.fromJson(json);
        return shape;
    }
}
