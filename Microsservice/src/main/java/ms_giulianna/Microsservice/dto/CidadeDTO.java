package ms_giulianna.Microsservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ms_giulianna.Microsservice.entity.Cidade;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CidadeDTO {

    private Long id;
    @NotBlank(message = "O nome da Cidade não pode ser vazio, nulo ou em branco")
    @Size(min=3,max=100,message = "Deve ter entre 3 a 100 caracteres")
    private String nome;
    @NotBlank(message = "O nome da Cidade não pode ser vazio, nulo ou em branco")
    @Size(min=2,max=2,message = "Deve ter 2 caracteres")
    private String uf;
    @NotBlank(message = "O nome da Cidade não pode ser vazio, nulo ou em branco")
    @Size(min=3,max=100,message = "Deve ter entre 3 a 100 caracteres")
    private String estado;

    public CidadeDTO(Cidade entity){
        id = entity.getId();
        nome = entity.getNome();
        estado = entity.getEstado();
        uf = entity.getUf();
    }

}
