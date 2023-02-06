package com.example.demo;

import java.io.*;
import java.util.Map;

import com.example.demo.DemoService.DemoService;
import com.example.demo.models.Shapes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SaveLoad {
    static ShapeManger manger=new ShapeManger();
    static DemoService service=new DemoService(manger);


    public static boolean saveJSON(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        FileWriter f = new FileWriter(path);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, Model.getDataBase());
        f.close();
        return true;
    }
    public static String[] loadJSON(String json) throws IOException, JSONException, ParseException, CloneNotSupportedException {
        JSONParser jsonParser=new JSONParser();
        String dd= jsonParser.parse(new FileReader(json)).toString();
        String object="";
        String type="";
        boolean inObject=false;
        for(int i=1;i<dd.length()-1;i++){
            if(dd.charAt(i)=='{') {
                inObject = true;
            }
            if(inObject) {
                object += dd.charAt(i);
            }
            if (dd.charAt(i)=='}') {
                inObject = false;
                for(int j=0;j<object.length()-8;j++) {
                    if(object.charAt(j)=='t'&&object.charAt(j+1)=='y'&&object.charAt(j+2)=='p'&&object.charAt(j+3)=='e'&&object.charAt(j+4)=='"'&&object.charAt(j+5)==':'&&object.charAt(j+6)=='"') {
                        char ch=object.charAt(j+7);
                        if(ch=='c')type="circle";
                        else if(ch=='s')type="square";
                        else if (ch=='r')type="rectangle";
                        else if(ch=='e')type="elipse";
                        else if (ch=='l')type="line";
                        else if (ch=='t')type="triangle";
                        break;
                    }
                }
                SaveLoad.service.create(type,object);
                type="";object="";
            }
        }
        String[] list=new String[Model.getDataBase().size()];
        int item=0;

        for (Map.Entry<String, Shapes> set :
                Model.getDataBase().entrySet()) {
            list[item++]=(undo_redo.konvaJson(set.getValue()));

        }
        return list;
    }

    public static boolean saveXML(String pathname) throws IOException {
        XmlMapper objectMapper = new XmlMapper();
        FileWriter f = new FileWriter(pathname);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, Model.getDataBase());
        f.close();
        return true;
    }

    public static String[] loadXML(String pathname) throws IOException, ParseException, CloneNotSupportedException {
        BufferedReader br =new BufferedReader(new FileReader(pathname));
        String xml ;
        StringBuilder sb = new StringBuilder();
        while ((xml=br.readLine())!=null){
            sb.append(xml.trim());
        }
        String text = sb.substring(9,sb.length()-10).toString();
        JSONObject json  = XML.toJSONObject(text);
        String dd = json.toString(0);
        String object="";
        String type="";
        boolean inObject=false;
        for(int i=1;i<dd.length()-1;i++){
            if(dd.charAt(i)=='{') {
                inObject = true;
            }
            if(inObject) {
                object += dd.charAt(i);
            }
            if (dd.charAt(i)=='}') {
                inObject = false;
                for(int j=0;j<object.length()-8;j++) {
                    if(object.charAt(j)=='t'&&object.charAt(j+1)=='y'&&object.charAt(j+2)=='p'&&object.charAt(j+3)=='e'&&object.charAt(j+4)=='"'&&object.charAt(j+5)==':'&&object.charAt(j+6)=='"') {
                        char ch=object.charAt(j+7);
                        if(ch=='c')type="circle";
                        else if(ch=='s')type="square";
                        else if (ch=='r')type="rectangle";
                        else if(ch=='e')type="elipse";
                        else if (ch=='l')type="line";
                        else if (ch=='t')type="triangle";
                        break;
                    }
                }
                SaveLoad.service.create(type,object);
                type="";object="";
            }
        }
        String[] list=new String[Model.getDataBase().size()];
        int item=0;

        for (Map.Entry<String, Shapes> set :
                Model.getDataBase().entrySet()) {
            list[item++]=undo_redo.konvaJson(set.getValue());

        }
        return list;
    }

}