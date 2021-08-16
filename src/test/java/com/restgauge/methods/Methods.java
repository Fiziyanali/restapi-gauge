package com.restgauge.methods;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;


public class Methods implements Paths{


    public void post(String firstName, String lastName, int subjectId){
        JSONObject request = new JSONObject();
        request.put("firstName", firstName);
        request.put("lastName", lastName);
        request.put("subjectId", subjectId);
        given()
                .header("Content-Type", "application/json")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(request.toJSONString())
        .when().post(userPath)
                .then().statusCode(201).log().body();
   }

    public void deleteById(int id){
        when().delete(userPath+ id).then().statusCode(200);
    }

    public void getAllUsers(){
        given().get(userPath).then().statusCode(200).log().body();
    }

    public void getAllSubjects(){
        given().get(subjectPath).then().statusCode(200).log().body();
    }

    public void getUserByFirstname(String name){
        given().param("firstName",name)
                .get(userPath)
        .then().statusCode(200)
                .log().body();
    }

    public void updateLastname(String lastName, int id){
        //patch güncelleme işlemi için kullanılır
        JSONObject request = new JSONObject();
        request.put("lastName", lastName);

        given()
                .header("Content-Type", "application/json")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(request.toJSONString())
        .when().patch(userPath+id)
                .then().statusCode(200).log().body();
    }

    public void put(String firstName, String lastName, int subjectId, int id){
        //PUT aynı kaynağa aynı adres ile erişilir ve eğer içerik var ise gelen veriler ile değiştirilir , eğer içerik yok ise yeni içerik yaratılır.
        JSONObject request = new JSONObject();
        request.put("firstName", firstName);
        request.put("lastName", lastName);
        request.put("subjectId", subjectId);

        given()
                .header("Content-Type", "application/json")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(request.toJSONString())
        .when().put(userPath+id)
                .then().statusCode(200).log().body();
    }
}