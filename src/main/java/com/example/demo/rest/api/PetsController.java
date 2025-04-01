package com.example.demo.rest.api;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.api.PetsApi;
import com.example.demo.rest.model.Pet;
import com.example.demo.rest.exception.PetStoreException;

@RestController
public class PetsController implements PetsApi {
	private static final Map<Long, Pet> PETS = Map.of(
			1L, new Pet(1L, "Dog"),
			2L, new Pet(2L, "Cat"),
			3L, new Pet(3L, "Hamster"));

	@Override
	public ResponseEntity<Void> createPets(Pet pet) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<List<Pet>> listPets(Integer limit) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

	}

	@Override
	public ResponseEntity<Pet> showPetById(String petId) {
		Long id;
		try {
			id = Long.parseLong(petId);
		} catch (NumberFormatException e) {
			throw new PetStoreException(400, "Invalid ID supplied");
		}

		Pet pet = PETS.get(id);
		if (Objects.isNull(pet)) {
			throw new PetStoreException(404, "Pet not found");
		}
		return ResponseEntity.ok(pet);
	}

	@ExceptionHandler(PetStoreException.class)
	public ResponseEntity<Error> handleException(PetStoreException e) {
		return ResponseEntity.status(e.getCode()).body(e.toError());
	}
}
