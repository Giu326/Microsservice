package ms_giulianna.Microsservice.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ms_giulianna.Microsservice.entity.Evento;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class EventoDTO {


    private Long id;
    @Size(min = 3, max = 100, message = "Deve ter entre 3 a 100 caracteres")
    private String nome;
    @Size(min = 10, max = 255, message = "Deve ter entre 10 a 255 caracteres")
    private String url;
    private LocalDate data;

    public EventoDTO(Evento entity){
        id = entity.getId();
        nome = entity.getNome();
        url = entity.getUrl();
        data = entity.getData();
    }

}
