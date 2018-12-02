package com.five_x.common;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONLoaderDistrict {
    private JSONParser parser;
    private ArrayList<District> districts;

    public JSONLoaderDistrict() {
        this.parser = new JSONParser();
        this.districts = new ArrayList<>();
    }

    public ArrayList<District> getDistricts(){
        if(parser == null){
            return null;
        } else {
            Object a = null;
            try {
                a = parser.parse(new FileReader("databases\\Naselenie_kvartali.geojson"));
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            JSONObject collections = (JSONObject) a;
            JSONArray features = (JSONArray) collections.get("features");
            for (Object feature : features)
            {
                JSONObject feature1 = (JSONObject)feature;
                Object property =   feature1.get("properties");
                String regName = (String)(((JSONObject)property).get("RegName"));
                long peopleCount = (long)(((JSONObject)property).get("Broi_Lica"));
                double area = (double)(((JSONObject)property).get("Area_m2"));

                District district = new District(regName,(int)peopleCount,area);
                Object geometry = feature1.get("geometry");

                JSONArray coordinatesArray = (JSONArray)(((JSONObject)geometry).get("coordinates"));
                String coordinates = coordinatesArray.toString().replaceAll("\\[|]" ,"").trim();
                String regex = "(,*)(\\d+.\\d+)(,?)";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(coordinates);
                int i = 0;
                double x = 0;
                double y = 0;
                while(m.find()){
                    if(i%2==0){
                        x = Double.parseDouble(m.group(2));
                    } else {
                        y = Double.parseDouble(m.group(2));
                        district.addToPolygon((int)x,(int)y);
                    }
                    i++;
                }
                districts.add(district);

            }
        }
        return districts;
    }

    public ArrayList<District> queryDistrict(String name){
        ArrayList<District> districtsLocal = new ArrayList<>();
        for(District d : districts){
            if(d.getName().contains(name)){
                districtsLocal.add(d);
            }
        }
        return districtsLocal;
    }

}
