package com.example.demo.models;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Triangle extends Shapes{
    Integer sides=0;
    Double radius=0.0;
    public Triangle() {
        this.type="triangle";
        this.sides=3;
    }

    public Triangle(String id, Double x, Double y, String stroke, Integer strokeWidth, Boolean draggable, Double rotation, Double scaleX, Double scaleY, Double skewX, String fill, Double radius) {
        super(id, x, y, stroke, strokeWidth, draggable, rotation, scaleX, scaleY, skewX, fill);
        this.radius = radius;
        this.type="triangle";
        this.sides=3;

    }

    public Integer getSides() {
        return sides;
    }

    public void setSides(Integer sides) {
        this.sides = sides;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public boolean compareTo(Shapes o) {
        Triangle t = (Triangle) o;
        return super.compareTo(o)&& t.getSides()==this.sides&&Math.abs(t.getRadius()-this.getRadius())<1e-18;
    }

    @Override
    public Triangle clone() throws CloneNotSupportedException {
        Triangle shape =new Triangle();
        shape.setId(this.id);
        shape.setX(this.x);
        shape.setY(this.y);
        shape.setScaleX(this.scaleX);
        shape.setScaleY(this.scaleY);
        shape.setRotation(this.rotation);
        shape.setSkewX(this.skewX);
        shape.setDraggable(this.draggable);
        shape.setStroke(this.stroke);
        shape.setStrokeWidth(this.strokeWidth);
        shape.setFill(this.fill);
        shape.setRadius(this.radius);
        return shape;
    }

    @Override
    public String toJson() throws JsonProcessingException {
        Shapes t =new Triangle(this.id,this.x,this.y,this.stroke,this.strokeWidth,this.draggable,this.rotation,this.scaleX,this.scaleY,this.skewX,this.fill,this.radius);
        String json= mapper.writeValueAsString(t);
        return json;
    }

    @Override
    public void fromJson(String json) throws JsonProcessingException {
        Triangle shape = mapper.readValue(json,Triangle.class);
        this.id = shape.getId();
        this.x = shape.getX();
        this.y = shape.getY();
        this.stroke = shape.getStroke();
        this.strokeWidth = shape.getStrokeWidth();
        this.draggable = shape.getDraggable();
        this.rotation = shape.getRotation();
        this.scaleX = shape.getScaleX();
        this.scaleY = shape.getScaleY();
        this.skewX = shape.getSkewX();
        this.fill=shape.getFill();
        this.radius=shape.getRadius();
    }


}
