package com.five_x.ui;

import com.five_x.common.District;
import com.five_x.common.JSONLoaderDistrict;
import com.five_x.common.XLSLoader;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
   @FXML
   private ComboBox comboBox1;
   @FXML
   private ComboBox comboBox2;
   @FXML
   private TextField peopleCount1;
    @FXML
    private TextField busCapacity1;
    @FXML
    private TextField chart1;
    @FXML
    private TextField peopleCount2;
    @FXML
    private TextField busCapacity2;
    @FXML
    private TextField chart2;


    private JSONLoaderDistrict districtLoader;
    private XLSLoader xlsLoader;
    @FXML
    public void initialize() {
        districtLoader = new JSONLoaderDistrict();
        districtLoader.getDistricts();
        xlsLoader = new XLSLoader();
    }

    @FXML
    public void handleButtonPressed1(){
        getFields(comboBox1, peopleCount1, busCapacity1, chart1);
    }

    private void getFields(ComboBox comboBox1, TextField peopleCount1, TextField busCapacity1, TextField chart1) {
        String selectedItemString = comboBox1.getSelectionModel().getSelectedItem().toString();
        int[] data = getSelectedItemData(selectedItemString);
        peopleCount1.setText(String.valueOf(data[0]));
        busCapacity1.setText(String.valueOf(data[1]));
        double ratio = (double)data[0]/(double)data[1];
        chart1.setText(String.format("%.2f",ratio));
    }

    @FXML
    public void handleButtonPressed2() {
        getFields(comboBox2, peopleCount2, busCapacity2, chart2);
    }

    private int[] getSelectedItemData(String name){
        int[] data = new int[2];
        ArrayList<District> districts = districtLoader.queryDistrict(name);
        data[1]=xlsLoader.getCapacityForDistrict(name);
        for(District d : districts){
            data[0]+= d.getPeopleCount();
        }
        return data;
    }



}
