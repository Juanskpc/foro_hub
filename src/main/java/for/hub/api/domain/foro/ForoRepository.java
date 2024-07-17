package med.voll.api.domain.foro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForoRepository extends JpaRepository<med.voll.api.domain.foro.Foro, Long> {
    Page<med.voll.api.domain.foro.Foro> findByActivoTrue(Pageable paginacion);
}
