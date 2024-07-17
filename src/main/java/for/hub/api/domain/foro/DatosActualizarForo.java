package med.voll.api.domain.foro;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosActualizarForo(
        @NotNull Long id,
        String mensaje,
        String nombreCurso,
        String titulo) {
}
