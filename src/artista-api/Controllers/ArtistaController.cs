using artista_api.Infraestrutura;
using artista_api.Model;
using artista_api.Repository;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Net;

namespace artista_api.Controllers
{
    [Route("[controller]")]
    public class ArtistaController : ControllerBase
    {
        private readonly IArtistaRepository artistaRepository;

        public ArtistaController(IArtistaRepository artistaRepository)
        {
            this.artistaRepository = artistaRepository;
        }

        [HttpGet]
        public IActionResult Get()
        {
            try
            {
                return Ok(artistaRepository.GetAll());
            }
            catch (Exception ex)
            {
                return Problem(ex.Message);
            }
        }

        [HttpGet("{id}")]
        public IActionResult GetById(int Id)
        {
            try
            {
                var artista = artistaRepository.GetBy(Id);
                if (artista == null)
                    return NotFound();

                return Ok(artista);
            }
            catch (Exception ex)
            {
                return Problem(ex.Message);
            }
        }

        [HttpPost]
        public IActionResult Post([FromBody] Artista artista)
        {
            try
            {
                artistaRepository.Save(artista);
                return Created($"/artista/{artista.Id}", artista);
            }

            catch (Exception ex)
            {
                return Problem(ex.Message);
            }
        }
        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            try
            {
                var count = artistaRepository.Delete(id);
                if (count == 0)
                    return NotFound();

                return Ok();
            }
            catch (Exception ex)
            {
                return Problem(ex.Message);
            }
        }
        [HttpPut]
        public IActionResult Put([FromBody] Artista artista)
        {
            try
            {
                artistaRepository.Update(artista);
                return Ok();
            }

            catch (Exception ex)
            {
                return Problem(ex.Message);
            }
        }

    }
}
