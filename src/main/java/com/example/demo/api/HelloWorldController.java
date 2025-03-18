package com.example.demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController implements HelloWorldApi {

	@Override
	public ResponseEntity<String> helloWorld() {
		return new ResponseEntity<>("Hello World.", HttpStatus.OK);
	}

}
