package co.com.personacliente2021.service;

import java.util.List;

import co.com.personacliente2021.model.Persona;
import retrofit2.Call;
import retrofit2.http.GET;

 interface PersonaClient {
       @GET("/estudiantes")
       Call<List<Persona>> getPersonas();
}
