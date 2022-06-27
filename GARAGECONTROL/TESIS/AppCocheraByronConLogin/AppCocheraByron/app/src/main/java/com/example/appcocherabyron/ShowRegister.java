package com.example.appcocherabyron;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.appcocherabyron.adapter.CarsAdapter;
import com.example.appcocherabyron.adapter.RegisterAdapter;
import com.example.appcocherabyron.api.ApiClient;
import com.example.appcocherabyron.services.CarsResponse;
import com.example.appcocherabyron.services.RegisterResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowRegister extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    RecyclerView recyclerView;
    RegisterAdapter registerAdapter;
    String today,token;
    TextView volver;
    public static SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_register);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        recyclerView=findViewById(R.id.recyclerview2);
        volver=findViewById(R.id.tvolver);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        registerAdapter = new RegisterAdapter(this::ClickedRegister);


        getToday();
        getAllRegisters(today);
        Log.d("Hoy es ", today);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    public void getAllRegisters(String dia){
        preferences = getSharedPreferences(getPackageName()+ "_preferences", Context.MODE_PRIVATE);
        token="Bearer"+ " " + preferences.getString("token", "");

        Call<List<RegisterResponse>> registerList = ApiClient.getRegisterService().getRegisters(token,dia);
        registerList.enqueue(new Callback<List<RegisterResponse>>() {
            @Override
            public void onResponse(Call<List<RegisterResponse>> call, Response<List<RegisterResponse>> response) {
                if(response.isSuccessful()){
                    List<RegisterResponse> registerResponses = response.body();
                    registerAdapter.setData(registerResponses);
                    recyclerView.setAdapter(registerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<RegisterResponse>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });
    }

    public void ClickedRegister(RegisterResponse registerResponse) {
       /* Intent intent = new Intent(this, RegisterActivity.class);
        _user="jorge";
        _dni=dni;
        _registrationplate=carsResponse.getRegistrationplate();
        intent.putExtra("user", _user);
        intent.putExtra("dni", _dni);
        intent.putExtra("registration_plate", _registrationplate);
        startActivity(intent);*/

        /*startActivity(new Intent(this,RegisterActivity.class).putExtra("data",carsResponse));*/
        /*Log.e("usuario clic",_user +" "+ _dni+" "+ _registrationplate);*/

    }
    public String getToday(){
        long ahora = System.currentTimeMillis();
        Date fecha = new Date(ahora);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      return  today = df.format(fecha);


    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                String date2 = makeDateString2(year, month,day );
                Log.d("today is  ", date2);
                getAllRegisters(date2);
//                startActivity(new Intent(getApplicationContext(), ShowRegister.class));
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }
    private String makeDateString2(int year, int month, int day)
    {
        return year + "-" +getMonthFormat2(month) + "-" + day  ;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "ENE";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "ABR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AGO";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }
    private String getMonthFormat2(int month)
    {
        if(month == 1)
            return "01";
        if(month == 2)
            return "02";
        if(month == 3)
            return "03";
        if(month == 4)
            return "04";
        if(month == 5)
            return "05";
        if(month == 6)
            return "06";
        if(month == 7)
            return "07";
        if(month == 8)
            return "08";
        if(month == 9)
            return "09";
        if(month == 10)
            return "10";
        if(month == 11)
            return "11";
        if(month == 12)
            return "12";

        //default should never happen
        return "01";
    }
    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}