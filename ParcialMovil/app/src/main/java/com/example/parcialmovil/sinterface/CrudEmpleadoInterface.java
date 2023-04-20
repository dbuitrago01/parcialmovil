package com.example.parcialmovil.sinterface;

import com.example.parcialmovil.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CrudEmpleadoInterface {

    @GET("/ConsultarAll")
    Call<List<Empleado>> getAll();


}
