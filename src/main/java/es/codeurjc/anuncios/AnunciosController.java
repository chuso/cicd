package es.codeurjc.anuncios;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anuncios")
public class AnunciosController {

	@Autowired
	private AnunciosRepository repository;

	@PostConstruct
	public void init() {
		repository.save(new Anuncio("Pepe", "Hola caracola", "XXXX"));
		repository.save(new Anuncio("Juan", "Hola caracola", "XXXX"));
	}

	@GetMapping("/")
	public List<Anuncio> getAnuncios() {
		return repository.findAll();
	}

	@PostMapping("/")
	public Anuncio nuevoAnuncio(@RequestBody Anuncio anuncio) {
		return repository.save(anuncio);
	}

	@GetMapping("/{id}")
	public Anuncio verAnuncio(@PathVariable long id) {
		return repository.getOne(id);
	}
}