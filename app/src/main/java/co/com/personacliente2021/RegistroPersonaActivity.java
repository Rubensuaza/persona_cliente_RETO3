package co.com.personacliente2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.personacliente2021.dto.PersonaDTO;
import co.com.personacliente2021.model.Persona;
import co.com.personacliente2021.service.persona.PersonaServiceImpl;
import co.com.personacliente2021.service.tipodocumento.TipoDocumentoServiceImpl;
import co.com.personacliente2021.util.ActionBarUtil;

public class RegistroPersonaActivity extends AppCompatActivity {

    @BindView(R.id.txt_documento)
    EditText txtDocumento;

    @BindView(R.id.txt_nombre)
    EditText txtNombre;

    @BindView(R.id.txt_apellido)
    EditText txtApellido;

    @BindView(R.id.spinnerTipoDocumento)
    Spinner spinnerTipoDocumento;

    PersonaDTO persona;
    private Integer documentoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_persona);
        ButterKnife.bind(this);
        persona = new PersonaDTO();
        ActionBarUtil.getInstance(this, true).setToolBar(getString(R.string.registro_persona), getString(R.string.insertar));
        listarTiposDocumentos();
        onSelectItemSpinner();

    }

    private void onSelectItemSpinner() {
        spinnerTipoDocumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 documentoSeleccionado = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void listarTiposDocumentos() {
        TipoDocumentoServiceImpl tipoDocumentoService = new TipoDocumentoServiceImpl(this);
        tipoDocumentoService.getTipoDocumento(spinnerTipoDocumento);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            guardarInformacion();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void guardarInformacion() {
        PersonaServiceImpl personaService = new PersonaServiceImpl(this);
        persona.setIdTipoDocumento(documentoSeleccionado);
        persona.setNumeroDocumento(txtDocumento.getText().toString());
        persona.setNombre(txtNombre.getText().toString());
        persona.setApellido(txtApellido.getText().toString());
        personaService.insertar(persona);
    }
}