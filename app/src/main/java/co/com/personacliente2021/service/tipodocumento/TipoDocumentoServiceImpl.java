package co.com.personacliente2021.service.tipodocumento;

import android.content.Context;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.List;
import co.com.personacliente2021.model.TipoDocumento;
import co.com.personacliente2021.util.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TipoDocumentoServiceImpl extends RetrofitFactory {

    private Context context;

    public TipoDocumentoServiceImpl(Context context){
        super(context);
    }


    public void getTipoDocumento(Spinner spinner){
        Retrofit retrofit =  getRetrofitInstance();
        TipoDocumentoClient client = retrofit.create(TipoDocumentoClient.class);
        Call<List<TipoDocumento>> response = client.getTipoDocumento();
        response.enqueue(new Callback<List<TipoDocumento>>() {
            @Override
            public void onResponse(Call<List<TipoDocumento>> call, Response<List<TipoDocumento>> response) {

            }

            @Override
            public void onFailure(Call<List<TipoDocumento>> call, Throwable t) {
                Toast.makeText(getContext(), "Error de comunicación: "+ t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}
