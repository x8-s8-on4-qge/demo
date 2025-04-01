package com.example.demo.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.api.HelloWorldApi;

@RestController
public class HelloWorldController implements HelloWorldApi {

	@Override
	public ResponseEntity<String> helloWorld() {
		return new ResponseEntity<>("Hello World.", HttpStatus.OK);
	}

}
