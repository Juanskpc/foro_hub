package med.voll.api.domain.foro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Foro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private String nombreCurso;
    private String titulo;
    private Boolean activo;

    public Foro(med.voll.api.domain.foro.DatosRegistroForo datosRegistroForo) {
        this.activo = true;
        this.mensaje = datosRegistroForo.mensaje();
        this.nombreCurso = datosRegistroForo.nombreCurso();
        this.titulo = datosRegistroForo.titulo();
    }

    public void actualizarDatos(med.voll.api.domain.foro.DatosActualizarForo datosActualizarForo) {
        if (datosActualizarForo.mensaje() != null) {
            this.mensaje = datosActualizarForo.mensaje();
        }
        if (datosActualizarForo.nombreCurso() != null) {
            this.nombreCurso = datosActualizarForo.nombreCurso();
        }
        if (datosActualizarForo.titulo() != null) {
            this.titulo = datosActualizarForo.titulo();
        }
    }

    public void desactivarForo() {
        this.activo = false;
    }
}
