package com.example.demo;

import com.example.demo.models.Shapes;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Stack;

public class undo_redo {
    public static Stack<Shapes> st1 = new Stack<Shapes>(), st2 = new Stack<Shapes>();

    public static void add(Shapes s) {
        st2.clear();
        st1.push(s);
    }
    static ShapeManger  manger=new ShapeManger();
    public static String undo() throws JsonProcessingException {

        if (st1.empty()) return "NON";
        Shapes y = st1.pop();
        String ff = y.getId();
        if (Model.containShape(ff)) {
            Shapes z = Model.getShape(ff);
            if (y.compareTo(z) ) {
                Model.delete(ff);
                st2.push(y);
                return y.getId()+"delete";
            } else {
                st2.push(z);
                Model.addElement(y);
                return y.getId()+undo_redo.konvaJson(y);
            }
        } else {
            Model.addElement(y);
            return y.getId()+undo_redo.konvaJson(y);
        }

    }

    public static String redo() throws JsonProcessingException, CloneNotSupportedException {
        if (st2.empty())
            return "NON";
        else {
            Shapes sh=Model.getShape(st2.peek().getId());
            if(sh==null)sh=st2.peek();
            Shapes shape=manger.createShape(st2.peek().getType(),sh.toJson());
            st1.push(shape);
            Model.addElement(st2.peek());
            st2.pop();
            return st1.peek().getId()+undo_redo.konvaJson(Model.getShape(st1.peek().getId()));
        }
    }
    public static String konvaJson(Shapes  shape) throws JsonProcessingException {

        String json = shape.toJson();
        for (int i = 6; i < json.length(); i++) {
            if(json.charAt(i)=='\"'&&json.charAt(i-1)==':'&&json.charAt(i-2)=='\"'&&json.charAt(i-3)=='l'&&json.charAt(i-4)=='l'&&json.charAt(i-5)=='i'&&json.charAt(i-6)=='f'){
                json = json.substring(0,i+1)+"#"+json.substring(i+1);
                break;
            }
        }
        String condition =shape.getType();
        String className = "";
        switch (condition) {
            case "rectangle" -> className = "Rect";
            case "circle" -> className = "Circle";
            case "line" -> className = "Line";
            case "elipse" -> className = "Ellipse";
            case "triangle" -> className = "RegularPolygon";
            case "square" -> className = "Rect";
            default -> {
            }
        }
        json = "{\"attrs\":"+json+",\"className\":\""+className+"\"}";
        return json;
    }

}