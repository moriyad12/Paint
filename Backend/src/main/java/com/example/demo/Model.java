package com.example.demo;

import com.example.demo.models.Shapes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class Model {
    private static HashMap<String, Shapes> dataBase=new HashMap<String,Shapes>();
    protected static ObjectMapper mapper =new ObjectMapper();

    public Model() {

    }
    public static String toJson() throws JsonProcessingException {
        return Model.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Model.getDataBase());
    }
    public static void fromJson(String json) throws JsonProcessingException {
        TypeReference<HashMap<String, Shapes>> typeRef
                = new TypeReference<HashMap<String, Shapes>>() {};
        Model.dataBase = mapper.readValue(json,typeRef);
    }
    public static void addElement( Shapes shape){
        if(dataBase.containsKey(shape.getId())){
            dataBase.replace(shape.getId(),shape);
        }else
            dataBase.put(shape.getId(),shape);
    }
    public static Shapes getShape(String id){
        Shapes shapes= dataBase.get(id);
        return shapes;
    }
    public static boolean containShape(String id){
        return dataBase.containsKey(id);
    }
    public static HashMap<String, Shapes> getDataBase() {
        return dataBase;
    }
    public static void delete(String id){
        dataBase.remove(id);
    }

    public static void setDataBase(HashMap<String, Shapes> dataBase) {
        Model.dataBase = dataBase;
    }

    public static void clear(){
        dataBase.clear();
    }

}
