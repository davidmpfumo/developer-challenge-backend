package mz.gov.backenddeveloperchallenge.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.gov.backenddeveloperchallenge.model.Pais;
import mz.gov.backenddeveloperchallenge.repository.PaisRepository;

@RestController
@RequestMapping("/api")
class PaisController {

    private final Logger log = LoggerFactory.getLogger(PaisController.class);
    private PaisRepository paisRepository;

    public PaisController(PaisRepository groupRepository) {
        this.paisRepository = groupRepository;
    }

    @GetMapping("/paises")
    Collection<Pais> paises() {
    	 log.info("Requisicao para listar todos paises");
        return paisRepository.findAll();
    }
    
	@GetMapping("/paisesOrdenadosPorPropriedade/{properties}")
	Collection<Pais> paisesOrdenados(@PathVariable String... properties) {
		log.info("Requisicao para listar todos paises ordenados por propriedades:");
		Stream.of(properties).forEach(property -> System.out.println(property));
		return paisRepository.findAll(this.withProperties(properties));
	}

	private Sort withProperties(String... properties) {
		return Sort.by(properties).ascending();
	}
   

    @GetMapping("/pais/{id}")
    ResponseEntity<?> getPaisPorId(@PathVariable Long id) {
        Optional<Pais> pais = paisRepository.findById(id);
        return pais.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/pais")
    ResponseEntity<Pais> criarPais(@RequestBody Pais pais) throws URISyntaxException {
        log.info("Requisicao para criar pais: {}", pais);
        Pais result = paisRepository.save(pais);
        return ResponseEntity.created(new URI("/api/pais/" + result.getId()))
                .body(result);
    }

    @PutMapping("/pais/{id}")
    ResponseEntity<Pais> actualizarPais(@RequestBody Pais pais,@PathVariable Long id) {
    	log.info("Requisicao para actualizar pais: {}", id);
        Pais result = this.setDataToUpdate(pais, id);
        return ResponseEntity.ok().body(paisRepository.save(result));
    }

	private Pais setDataToUpdate(Pais pais, Long id) {
		Pais paisToSet = paisRepository.getOne(id);
		paisToSet.setArea(pais.getArea());
		paisToSet.setCapital(pais.getCapital());
		paisToSet.setRegiao(pais.getRegiao());
		paisToSet.setSubRegiao(pais.getSubRegiao());
		return paisToSet;
	}

    @DeleteMapping("/pais/{id}")
    public ResponseEntity<?> removerPais(@PathVariable Long id) {
    	log.info("Requisicao para remover pais: {}", id);
        paisRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}