package bordoesapi.bordoesapi.infraestrutura.persistencia;

import bordoesapi.bordoesapi.dominio.entidade.Audio;
import bordoesapi.bordoesapi.dominio.interfaces.InterfaceAudioService;
import bordoesapi.bordoesapi.infraestrutura.repositories.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class AudioPersistencia implements InterfaceAudioService {

    @Autowired
    private AudioRepository audioRepository;


    @Override
    public List<Audio> getAll() {
        return audioRepository.findAll();
    }

    @Override
    public Audio getById(int id) {
        return audioRepository.getReferenceById(id);
    }

    @Override
    public Audio save(Audio audio) {
        return audioRepository.save(audio);
    }

    @Override
    public Audio update(Audio audio) {
        return audioRepository.save(audio);
    }

    @Override
    public void delete(int id) {
        Audio audio = audioRepository.getReferenceById(id);
        audioRepository.delete(audio);
    }
}
