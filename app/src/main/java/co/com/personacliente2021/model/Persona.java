package co.com.personacliente2021.model;

import lombok.Data;

@Data
public class Persona {

      private Integer idEstudiante;
      private String  tipoDocumento;
      private String  numeroDocumento;
      private String  nombres;
      private String apellidos;
      private String instituacionEducativa;
}
