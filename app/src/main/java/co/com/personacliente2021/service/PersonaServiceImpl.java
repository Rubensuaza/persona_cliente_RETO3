package co.com.personacliente2021.service;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import co.com.personacliente2021.model.Persona;
import co.com.personacliente2021.util.Parametro;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

 public class PersonaServiceImpl {

     private Context context;

     public PersonaServiceImpl(Context context){
         this.context = context;
     }

    private final OkHttpClient simpleClient = new OkHttpClient.Builder()
            .readTimeout(Parametro.CONNECTION_TIMEOUT_RETROFIT, TimeUnit.SECONDS)
            .connectTimeout(Parametro.CONNECTION_TIMEOUT_RETROFIT, TimeUnit.SECONDS)
            .build();


    public void getPersona(ListView listViewPersonas){
        Retrofit retrofit =  new Retrofit.Builder().baseUrl(Parametro.URL_BASE).addConverterFactory(GsonConverterFactory.create()).client(simpleClient).build();
        PersonaClient client = retrofit.create(PersonaClient.class);
        Call<List<Persona>> response = client.getPersonas();
        response.enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                List<Persona> listaPersonas = response.body();
                String[] array = new String[listaPersonas.size()];
                for(int i = 0; i < listaPersonas.size(); i++){
                    array[i] = listaPersonas.get(i).getNombres() +" "+ listaPersonas.get(i).getApellidos()+" Institución " + listaPersonas.get(i).getInstituacionEducativa();
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item,array);
                listViewPersonas.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {
                Toast.makeText(context, "Error de comunicación", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
