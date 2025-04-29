package ms_giulianna.Microsservice.service;
import jakarta.persistence.EntityNotFoundException;
import ms_giulianna.Microsservice.dto.CidadeDTO;
import ms_giulianna.Microsservice.entity.Cidade;
import ms_giulianna.Microsservice.repository.CidadeRepository;
import ms_giulianna.Microsservice.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Transactional(readOnly = true)
    public List<CidadeDTO> getAll(){
        return repository.findAll().stream().map(CidadeDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CidadeDTO getById(Long id){
        Cidade entity = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new CidadeDTO(entity);
    }

    @Transactional
    public CidadeDTO createCidade(CidadeDTO dto){
        Cidade entity = new Cidade();
        CopyDtoToEntity(entity,dto);
        entity = repository.save(entity);
        return new CidadeDTO(entity);
    }

    @Transactional
    public CidadeDTO update(Long id, CidadeDTO dto){
        try {
            Cidade entity = repository.getReferenceById(id);
            CopyDtoToEntity(entity,dto);
            entity = repository.save(entity);
            return new CidadeDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        repository.deleteById(id);
    }


    private void CopyDtoToEntity(Cidade entity, CidadeDTO dto) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setUf(dto.getUf());
        entity.setEstado(dto.getEstado());
    }
}
