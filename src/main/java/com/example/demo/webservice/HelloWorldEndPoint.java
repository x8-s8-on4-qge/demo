package com.example.demo.webservice;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HelloWorldEndPoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHelloWorldRequest")
	@ResponsePayload
	public GetHelloWorldResponse getHelloWorld(@RequestPayload GetHelloWorldRequest request) {
		GetHelloWorldResponse response = new GetHelloWorldResponse();
		response.setMessage("Hello World via SOAP protocol!");

		return response;
	}
}
