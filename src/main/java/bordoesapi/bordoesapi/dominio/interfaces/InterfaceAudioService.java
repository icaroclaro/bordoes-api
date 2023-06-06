package bordoesapi.bordoesapi.dominio.interfaces;

import bordoesapi.bordoesapi.dominio.entidade.Audio;

import java.util.List;

public interface InterfaceAudioService {

    public List<Audio> getAll();
    public Audio getById(int id);
    public Audio save(Audio audio);
    public Audio update(Audio audio);
    public void delete(int id);
}
