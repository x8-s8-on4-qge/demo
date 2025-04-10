package com.example.demo.webservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.demo.webservice.GetHelloWorldRequest;
import com.example.demo.webservice.GetHelloWorldResponse;

@Endpoint
public class HelloWorldEndPoint {

	private static final String NAMESPACE_URI = "https://demo-latest-owxa.onrender.com";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHelloWorldRequest")
	@ResponsePayload
	public GetHelloWorldResponse getHelloWorld(@RequestPayload GetHelloWorldRequest request) {
		GetHelloWorldResponse response = new GetHelloWorldResponse();
		response.setMessage("Hello World via SOAP protocol.");

		return response;
	}
}
