package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.foro.*;
import med.voll.api.domain.foro.DatosActualizarForo;
import med.voll.api.domain.foro.DatosListadoForo;
import med.voll.api.domain.foro.DatosRegistroForo;
import med.voll.api.domain.foro.DatosRespuestaForo;
import med.voll.api.domain.foro.Foro;
import med.voll.api.domain.foro.ForoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class ForoController {

    @Autowired
    private ForoRepository foroRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaForo> registrarMedico(@RequestBody @Valid DatosRegistroForo datosRegistroForo,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        Foro foro = foroRepository.save(new Foro(datosRegistroForo));
        DatosRespuestaForo datosRespuestaForo = new DatosRespuestaForo(foro.getId(), foro.getMensaje(), foro.getNombreCurso(),
                foro.getTitulo());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(foro.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaForo);

    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoForo>> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(foroRepository.findByActivoTrue(paginacion).map(DatosListadoForo::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarForo(@RequestBody @Valid DatosActualizarForo datosActualizarForo) {
        Foro foro = foroRepository.getReferenceById(datosActualizarForo.id());
        foro.actualizarDatos(datosActualizarForo);
        return ResponseEntity.ok(new DatosRespuestaForo(foro.getId(), foro.getMensaje(), foro.getNombreCurso(),
                foro.getTitulo()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarForo(@PathVariable Long id) {
        Foro foro = foroRepository.getReferenceById(id);
        foro.desactivarForo();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaForo> retornaDatosForo(@PathVariable Long id) {
        Foro foro = foroRepository.getReferenceById(id);
        var datosForo = new DatosRespuestaForo(foro.getId(), foro.getMensaje(), foro.getNombreCurso(),
                foro.getTitulo());
        return ResponseEntity.ok(datosForo);
    }

}
