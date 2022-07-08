package com.example.salon_app.Services;

import com.example.salon_app.Model.Customer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CustomerApi {
    @POST("/api/customers/")
    Call<Customer> save(@Body Customer customer);
}
