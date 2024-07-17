package med.voll.api.domain.foro;

public record DatosListadoForo(
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email) {

    public DatosListadoForo(med.voll.api.domain.foro.Foro foro) {
        this(foro.getId(), foro.getNombre(), foro.getEspecialidad().toString(), foro.getDocumento(), foro.getEmail());
    }
}


