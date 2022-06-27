package com.example.appcocherabyron.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersService {

    @GET("cars/43963179")
    /*@GET("/")*/
    Call<List<UsersResponse>> getPerson();
    /*Call<PersonaResponse> getPerson();*/
}
