package com.example.demo.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.demo.webservice.DeleteProductRequest;
import com.example.demo.webservice.DeleteProductResponse;
import com.example.demo.webservice.GetProductListRequest;
import com.example.demo.webservice.GetProductListResponse;
import com.example.demo.webservice.RegistProductRequest;
import com.example.demo.webservice.RegistProductResponse;
import com.example.demo.webservice.UpdateProductRequest;
import com.example.demo.webservice.UpdateProductResponse;
import com.example.demo.webservice.service.ProductService;

@Endpoint
public class ProductEndPoint {

	private static final String NAMESPACE_URI = "https://demo-latest-owxa.onrender.com";

	@Autowired
	ProductService productService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "registProductRequest")
	@ResponsePayload
	public RegistProductResponse registProduct(@RequestPayload RegistProductRequest request) {
		return productService.registProduct(request.getName(), request.getPrice(), request.getDescription());
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductListRequest")
	@ResponsePayload
	public GetProductListResponse getProductList(@RequestPayload GetProductListRequest request) {
		return productService.getProductList(request.getName(), request.getPrice(), request.getDescription(),
				request.getCreatedAt(), request.getUpdatedAt());
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
	@ResponsePayload
	public UpdateProductResponse updateProduct(@RequestPayload UpdateProductRequest request) {
		return productService.updateProduct(request.getProductId(), request.getName(), request.getPrice(),
				request.getDescription());
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductRequest")
	@ResponsePayload
	public DeleteProductResponse deleteProduct(@RequestPayload DeleteProductRequest request) {
		return productService.deleteProduct(request.getProductId());
	}

}
