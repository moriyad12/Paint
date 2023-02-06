package com.example.demo.models;

import java.io.Serializable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Shapes implements Cloneable,Serializable  {
    protected ObjectMapper mapper =new ObjectMapper();
    protected String id="";
    protected Double x=0.0;
    protected Double y=0.0;
    protected String stroke="";
    protected Integer strokeWidth=0;
    protected Boolean draggable=false;
    protected Double rotation=0.0;
    protected Double scaleX=1.0;
    protected Double scaleY=1.0;
    protected Double skewX=0.0;
    protected  String type="circle";
    protected String fill="";

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public Shapes() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public Integer getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(Integer strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Boolean getDraggable() {
        return draggable;
    }

    public void setDraggable(Boolean draggable) {
        this.draggable = draggable;
    }

    public Double getRotation() {
        return rotation;
    }

    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }

    public Double getScaleX() {
        return scaleX;
    }

    public void setScaleX(Double scaleX) {
        this.scaleX = scaleX;
    }

    public Double getScaleY() {
        return scaleY;
    }

    public void setScaleY(Double scaleY) {
        this.scaleY = scaleY;
    }

    public Double getSkewX() {
        return skewX;
    }

    public void setSkewX(Double skewX) {
        this.skewX = skewX;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean compareTo(Shapes o) {
        if(o==null)return false;
        Shapes shapes = (Shapes) o;

        return shapes.getFill().equals(this.fill)&&Math.abs(this.x-shapes.getX())<1e-18 && Math.abs(this.y-shapes.getY())<1e-18 &&Math.abs(this.rotation-shapes.getRotation())<1e-18&& Math.abs(this.scaleX-shapes.getScaleX())<1e-18&&Math.abs(this.scaleY-shapes.getScaleY())<1e-18&&Math.abs(this.skewX-shapes.getSkewX())<1e-18 &&this.getId().equals(shapes.getId()) ;
    }
    public Object clone() throws CloneNotSupportedException {
        Shapes shape =new Shapes();
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
        return shape;
    }
    public Shapes(String id, Double x, Double y, String stroke, Integer strokeWidth, Boolean draggable, Double rotation, Double scaleX, Double scaleY, Double skewX,String fill) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.stroke = stroke;
        this.strokeWidth = strokeWidth;
        this.draggable = draggable;
        this.rotation = rotation;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.skewX = skewX;
        this.fill=fill;

    }
    public String toJson() throws JsonProcessingException {
        Shapes shape =new Shapes(this.id,this.x,this.y,this.stroke,this.strokeWidth,this.draggable,this.rotation,this.scaleX,this.scaleY,this.skewX,this.fill);
        String json= mapper.writeValueAsString(shape);
        return json;
    }
    public void fromJson(String json) throws JsonProcessingException {
        Shapes shape = mapper.readValue(json,Shapes.class);
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
    }

    @Override
    public String toString() {
        return "Shapes{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", stroke='" + stroke + '\'' +
                ", strokeWidth=" + strokeWidth +
                ", draggable=" + draggable +
                ", rotation=" + rotation +
                ", scaleX=" + scaleX +
                ", scaleY=" + scaleY +
                ", skewX=" + skewX +
                ", type='" + type + '\'' +
                ", fill='" + fill + '\'' +
                '}';
    }
}
