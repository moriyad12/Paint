package com.example.demo.models;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Line extends Shapes{
    double height=0.0;
    double width=0.0;
    public Line() {
        this.type="line";
    }
    public Line(String id, Double x, Double y, String stroke, Integer strokeWidth, Boolean draggable, Double rotation, Double scaleX, Double scaleY, Double skewX, String fill, double height, double width) {
        super(id, x, y, stroke, strokeWidth, draggable, rotation, scaleX, scaleY, skewX, fill);
        this.height = height;
        this.width = width;
        this.type="square";
    }


    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public boolean compareTo(Shapes o) {
        Square rec =(Square) o;
        return super.compareTo(o)&&Math.abs(rec.height-this.getHeight())<1e-18&&Math.abs(rec.getWidth()-this.width)<1e-18;
    }

    @Override
    public Square clone() throws CloneNotSupportedException {
        Square shape=new Square();
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
        shape.setHeight(this.height);
        shape.setWidth(this.width);
        return shape;
    }

    @Override
    public String toJson() throws JsonProcessingException {
        Shapes shape =new Square(this.id,this.x,this.y,this.stroke,this.strokeWidth,this.draggable,this.rotation,this.scaleX,this.scaleY,this.skewX,this.fill,this.height,this.width);
        String json= mapper.writeValueAsString(shape);
        return json;
    }

    @Override
    public void fromJson(String json) throws JsonProcessingException {
        Rectangle shape = mapper.readValue(json,Rectangle.class);
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
        this.height=shape.getHeight();
        this.width= shape.getWidth();
    }

}
