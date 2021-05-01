package co.com.personacliente2021.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.personacliente2021.MainActivity;
import co.com.personacliente2021.R;
import co.com.personacliente2021.RegistroPersonaActivity;
import co.com.personacliente2021.dto.PersonaDTO;
import co.com.personacliente2021.model.Persona;
import co.com.personacliente2021.service.persona.PersonaServiceImpl;

public class PersonaAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private Context context;
    private final List<Persona> personas;

    public PersonaAdapter(Context context, List<Persona> personas) {
        inflater = LayoutInflater.from(context);
        this.personas = personas;
        this.context=context;
    }

    @Override
    public int getCount() {
        return personas.size();
    }

    @Override
    public Persona getItem(int position) {
        return personas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return personas.get(position).getIdPersona().longValue();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PersonaDTO personaDTO=new PersonaDTO();
        personaDTO.setIdPersona(personas.get(position).getIdPersona());
        personaDTO.setIdTipoDocumento(personas.get(position).getTipoDocumento().getIdTipoDocumento());
        personaDTO.setNombre(personas.get(position).getNombre());
        personaDTO.setApellido(personas.get(position).getApellido());
        personaDTO.setNumeroDocumento(personas.get(position).getNumeroDocumento());
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.item_persona, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.numeroDocumento.setText(personas.get(position).getNumeroDocumento());
        holder.nombre.setText(personas.get(position).getNombre());
        holder.apellido.setText(personas.get(position).getApellido());
        View finalConvertView=convertView;
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(false);
                builder.setTitle(R.string.confirm);
                builder.setMessage(R.string.confirm_message_guardar_informacion);
                builder.setPositiveButton(R.string.confirm_action, (dialog, which) ->  eliminar(personaDTO.getIdPersona()) );
                builder.setNegativeButton(R.string.cancelar, (dialog, which) ->  dialog.cancel() );
                AlertDialog dialog = builder.create();
                dialog.show();





            }

        });


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, RegistroPersonaActivity.class);
                intent.putExtra("persona",personaDTO);
                context.startActivity(intent);


            }
        });


        return convertView;

    }

    private void eliminar(int id){
        PersonaServiceImpl personaService=new PersonaServiceImpl(context);
        personaService.eliminar(id);
        Intent intent=new Intent(context, MainActivity.class );
        context.startActivity(intent);
    }






    class ViewHolder {
        @BindView(R.id.txtNumeroDocumento)
        TextView numeroDocumento;
        @BindView(R.id.txNombre)
        TextView nombre;
        @BindView(R.id.txtApellido)
        TextView apellido;
        @BindView(R.id.imageButton)
        ImageButton eliminar;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
