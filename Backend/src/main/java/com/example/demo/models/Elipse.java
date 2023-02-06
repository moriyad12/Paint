package com.example.demo.models;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Elipse extends Shapes{
    private double radiusX=0.0;
    private double radiusY=0.0;

    public double getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }

    public Elipse(String id, Double x, Double y, String stroke, Integer strokeWidth, Boolean draggable, Double rotation, Double scaleX, Double scaleY, Double skewX, String fill, Double radiusX, Double radiusY) {
        super(id, x, y, stroke, strokeWidth, draggable, rotation, scaleX, scaleY, skewX, fill);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.type="elipse";
    }

    public Elipse() {
        this.type="elipse";
    }


    @Override
    public boolean compareTo(Shapes o) {
        Elipse e =(Elipse) o;
        return super.compareTo(o)&&Math.abs(e.getRadiusX()-this.radiusX)<1e-18 && Math.abs(e.getRadiusY()-this.radiusY)<1e-18;
    }

    @Override
    public Elipse clone() throws CloneNotSupportedException {
        Elipse shape =new Elipse();
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
        shape.setRadiusX(this.radiusX);
        shape.setRadiusY(this.radiusY);
        return shape;
    }

    @Override
    public String toJson() throws JsonProcessingException {
        Shapes shape = new Elipse(this.id, this.x, this.y, this.stroke, this.strokeWidth, this.draggable, this.rotation, this.scaleX, this.scaleY, this.skewX, this.fill, this.radiusX,this.radiusY);
        return    mapper.writeValueAsString(shape);

    }

    @Override
    public void fromJson(String json) throws JsonProcessingException {
        Elipse shape = mapper.readValue(json, Elipse.class);
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
        this.radiusX=shape.getRadiusX();
        this.radiusY=shape.getRadiusY();

    }

}
