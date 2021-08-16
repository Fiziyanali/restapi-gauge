package com.restgauge.step;

import com.restgauge.methods.Methods;
import com.restgauge.utils.ExcelUtils;
import com.thoughtworks.gauge.Step;

public class StepImplementation {

    Methods methods;
    private final String excelPath = "./src/test/java/com/restgauge/dal/TestData.xlsx";
    private String sheetName = "Sayfa1";
    ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

    public StepImplementation() {
        methods = new Methods();
    }

    @Step("Kullanici bilgilerini listele")
    public void getAllUsers()  {
        methods.getAllUsers();
    }

    @Step("Konu bilgilerini listele")
    public void getAllsubjects()  {
        methods.getAllSubjects();
    }

    @Step("<firstName> isimli kullanicilarin bilgilerini getir")
    public void getByFirstname(String firstName)  {
        methods.getUserByFirstname(firstName);
    }

    @Step("<firstName>, <lastName> ve <subjectId> bilgilerini ekle")
    public void post(String firstName, String lastName, int subjectId)  {
        methods.post(firstName,lastName,subjectId);
    }

    @Step("ID'si <id> olan verinin soyadini <lastName> olarak guncelle")
    public void patch(int id, String lastName)  {
        methods.updateLastname(lastName,id);
    }

    @Step("ID'si <id> olan veriyi <firstName>, <lastName> ve <subjectId> olarak guncelle")
    public void put(int id, String firstName, String lastName, int subjectId)  {
        methods.put(firstName, lastName, subjectId, id);
    }

    @Step("ID'si <id> olan veriyi sil")
    public void deleteById(int id)  {
        methods.deleteById(id);
    }

    @Step("<column> sutunlu ve <row> satirli Excel verilerini görüntüle")
    public void excelData(int column, int row)  {
        for (int i=0; i<row; i++) {
            System.out.println("*********************************");
            for (int j = 0; j < column; j++) {
                System.out.println(excel.getCellData(i,j));
            }
        }
    }

    @Step("Excel satir bilgisini görüntüle")
    public void excelRowCount()  {
        excel.getRowCount();
    }

    @Step("Excel sayfa bilgisini görüntüle")
    public void excelSheetName()  {
        excel.getSheetName();
    }

    @Step("<column> sutunlu ve <row> satirli Excel verilerini sisteme ekle")
    public void postFromExcel(int column, int row)  {
        String data[] = new String[3];
        for (int i=0; i<row; i++) { //Satır
            for (int j = 0; j < column; j++) { //sutun
                data[j] = excel.getCellData(i,j);
            }
            post(data[0], data[1], Integer.parseInt(data[2]));
        }
    }
}
