package com.example.demo;

import com.example.demo.models.*;

public class ShapeFactory {
    Shapes createShape(String type){
        if(type.equals("circle")){
            return new Circle();
        }else if(type.equals("line")){
            return new Line();
        }else if(type.equals("square")){
            return new Square();
        }else if(type.equals("triangle")){
            return new Triangle();
        }else if(type.equals("elipse")){
            return new Elipse();
        }else if(type.equals("rectangle")) {
            return new Rectangle();
        }else return null;
    }
}
