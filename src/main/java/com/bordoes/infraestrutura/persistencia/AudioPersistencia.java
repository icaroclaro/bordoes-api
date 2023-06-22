package com.bordoes.infraestrutura.persistencia;

import com.bordoes.dominio.entidade.Audio;
import com.bordoes.dominio.interfaces.InterfaceAudioService;
import com.bordoes.infraestrutura.repositories.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
