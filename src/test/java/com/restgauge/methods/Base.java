package com.restgauge.methods;

import com.thoughtworks.gauge.BeforeSuite;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;


public class Base {
    public static RequestSpecification endpointRequest;
    public static ResponseSpecification endpointResponse;

    @BeforeSuite
    public static void setUp(){
        endpointRequest = new RequestSpecBuilder()
                .setBaseUri("http://localhost:3000/")
                .addFilter(new RequestLoggingFilter())
                .build();

        endpointResponse = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(3000L))
                .build();
        RestAssured.requestSpecification = endpointRequest;
        RestAssured.responseSpecification = endpointResponse;
    }
}


