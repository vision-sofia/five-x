package com.five_x.common;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XLSLoader {

    public int getCapacityForDistrict(String userInput) {
        Bus bus = null;
        List<Bus> busList = new ArrayList<>();
        try {
            int cellCounter = 0;
            int i = 0;
            FileInputStream fis = new FileInputStream(new File("databases\\busesData.xls"));
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheetAt(0);
            FormulaEvaluator fEvaluator = wb.getCreationHelper().createFormulaEvaluator();
            int[] arrayInt = new int[2];
            String path = " ";
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (fEvaluator.evaluateInCell(cell).getCellType() == CellType.NUMERIC) {
                        arrayInt[i] = (int) cell.getNumericCellValue();
                        i++;
                        if (i == 2) {
                            i = 0;
                        }
                    }
                    if (fEvaluator.evaluateInCell(cell).getCellType() == CellType.STRING) {
                        path = cell.getStringCellValue();
                    }
                }
                bus = new Bus(arrayInt[0], path, arrayInt[1]);
                busList.add(bus);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return allCapacity(busList,userInput);
    }

    private  int allCapacity(List<Bus> busList,String userInput) {
        int sum = 0;
        for (int i = 0; i < busList.size(); i++) {
            String[] hoods = busList.get(i).getBusPath().split("-");
            for (String neighbourhood : hoods) {
                if (neighbourhood.contains(userInput)) {
                    sum += busList.get(i).getCapacity();
                }
            }
        }
        return sum;
    }
}