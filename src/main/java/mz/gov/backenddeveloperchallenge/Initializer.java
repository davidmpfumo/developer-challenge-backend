package mz.gov.backenddeveloperchallenge;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import mz.gov.backenddeveloperchallenge.model.Pais;
import mz.gov.backenddeveloperchallenge.repository.PaisRepository;

@Component
class Initializer implements CommandLineRunner {

	private final PaisRepository repository;

	private final int MAX_CREATION_TIME = 1;

	public Initializer(PaisRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) {

		for (int i = 0; i < MAX_CREATION_TIME; i++) {

			repository.deleteAll();
			Stream.of(new Pais("Mozambique", "Maputo", "Africa Austral", "Africa Austral", "Africa Austral"),
					new Pais("Africa do sul", "Johannesburg", "Africa Austral", "Africa Austral", "Africa Austral"),
					new Pais("Inglaterra", "Londres", "Europa", "Europa", "Europa"),
					new Pais("EUA", "Washington DC", "America", "America", "America"))
					.forEach(pais -> repository.save(pais));

			repository.findAll().forEach(System.out::println);

		}
	}
}