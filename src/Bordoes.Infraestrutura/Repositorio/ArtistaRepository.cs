using Bordoes.Dominio.Repositorio;
using Bordoes.Dominio.Entidade;
using Bordoes.Infraestrutura.DataContext;
using Microsoft.EntityFrameworkCore;

namespace Bordoes.Infraestrutura.Repositorio
{
    public class ArtistaRepository : IArtistaRepository
    {
        private readonly DataBaseContext dbContext;
        public ArtistaRepository(DataBaseContext dbContext)
        {
            this.dbContext = dbContext;
        }

        public int Delete(int Id)
        {
            var artista = dbContext.Artista.Find(Id);
            if (artista == null)
                return 0;

            dbContext.Artista.Remove(artista);
            return dbContext.SaveChanges();
        }

        public IEnumerable<Artista> GetAll()
        {
            return dbContext.Artista.ToList();
        }
        public Artista? GetBy(int Id)
        {
            return dbContext.Artista.Find(Id);

        }

        public void Save(Artista artista)
        {
            dbContext.Artista.Add(artista);
            dbContext.SaveChanges();
        }

        public void Update(Artista artista)
        {
            dbContext.Artista.Update(artista);
            dbContext.SaveChanges();
        }
    }
}
