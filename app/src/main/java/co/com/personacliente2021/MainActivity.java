package co.com.personacliente2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.personacliente2021.model.Persona;
import co.com.personacliente2021.service.PersonaServiceImpl;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listViewPersonas)
    ListView listViewPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PersonaServiceImpl personaService = new PersonaServiceImpl(this);
        personaService.getPersona(listViewPersonas);
    }
}