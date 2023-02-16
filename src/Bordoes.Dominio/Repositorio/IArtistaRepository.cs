using Bordoes.Dominio.Entidade;

namespace Bordoes.Dominio.Repositorio
{
    public interface IArtistaRepository
    {
        public IEnumerable<Artista> GetAll();
        public Artista? GetBy(int Id);
        public void Save(Artista artista);
        public int Delete(int Id);
        public void Update(Artista artista);
    }
}
