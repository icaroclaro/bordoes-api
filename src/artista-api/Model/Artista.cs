using System.ComponentModel.DataAnnotations;
using System.Diagnostics.CodeAnalysis;

namespace artista_api.Model
{
    public class Artista
    {
        [Key]
        public int Id { get; set; }
        public string? Nome { get; set; }
        public string? Detalhe { get; set; }
        public string? Instagram { get; set; }
        public bool Habilitado { get; set; }
    }
}