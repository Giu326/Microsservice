package ms_giulianna.Microsservice.service;
import jakarta.persistence.EntityNotFoundException;
import ms_giulianna.Microsservice.dto.EventoDTO;
import ms_giulianna.Microsservice.entity.Evento;
import ms_giulianna.Microsservice.repository.EventoRepository;
import ms_giulianna.Microsservice.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    @Transactional(readOnly = true)
    public List<EventoDTO> findAll(){
        List<Evento> eventos = repository.findAll();
        return eventos.stream().map(EventoDTO:: new).toList();
    }

    @Transactional(readOnly = true)
    public EventoDTO getById(Long id){
        Evento entity = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new EventoDTO(entity);
    }

    @Transactional
    public EventoDTO createEvento(EventoDTO dto){
        Evento entity = new Evento();
        CopyDtoToEntity(entity,dto);
        entity = repository.save(entity);
        return new EventoDTO(entity);
    }

    @Transactional
    public EventoDTO update(Long id, EventoDTO dto){
        try {
            Evento entity = repository.getReferenceById(id);
            CopyDtoToEntity(entity, dto);
            entity = repository.save(entity);
            return new EventoDTO(entity);
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

    private void CopyDtoToEntity(Evento entity, EventoDTO dto) {
        entity.setUrl(dto.getUrl());
        entity.setId(dto.getId());
        entity.setData(dto.getData());
        entity.setNome(dto.getNome());
    }
}
