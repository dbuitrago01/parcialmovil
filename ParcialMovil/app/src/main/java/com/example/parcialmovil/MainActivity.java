package com.example.parcialmovil;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parcialmovil.model.Empleado;
import com.example.parcialmovil.sinterface.CrudEmpleadoInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    CrudEmpleadoInterface cruempleado;
    List<Empleado> ListEmpleado = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void getAll(){
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("http://172.21.224.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<List<Empleado>> call = cruempleado.getAll();


        call.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {

                if(!response.isSuccessful()){
                    //System.out.println(response.message());
                    Log.e("Response err: ,", response.message());
                    return;
                }
                ListEmpleado = response.body();
                ListEmpleado.forEach(p-> System.out.println(p.toString()));

            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {

                  //System.out.println(t.getMessage());
                Log.e("Throw err: ,",t.getMessage());
            }
        });

    }

}