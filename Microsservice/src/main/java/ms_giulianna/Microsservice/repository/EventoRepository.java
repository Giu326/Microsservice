package ms_giulianna.Microsservice.repository;

import ms_giulianna.Microsservice.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
