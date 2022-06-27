package com.example.cocheraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cocheraapp.Utils.AsistenciaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Asistencia extends AppCompatActivity {
    AsistenciaService asistenciaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);
    }

    public void ListarPersonas(){
        Call<List<Asistencia>>call=asistenciaService.getAsistencia();
        call.enqueue(new Callback<List<Asistencia>>() {
            @Override
            public void onResponse(Call<List<Asistencia>> call, Response<List<Asistencia>> response) {

            }

            @Override
            public void onFailure(Call<List<Asistencia>> call, Throwable t) {

            }
        });
    }
}