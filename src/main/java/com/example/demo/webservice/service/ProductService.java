package com.example.demo.webservice.service;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.ProductDao;
import com.example.demo.webservice.DeleteProductResponse;
import com.example.demo.webservice.GetProductListResponse;
import com.example.demo.webservice.RegistProductResponse;
import com.example.demo.webservice.UpdateProductResponse;

@Component
public class ProductService {

	@Autowired
	ProductDao productDao;

	public RegistProductResponse registProduct(String name, BigDecimal price, String description) {
		RegistProductResponse response = new RegistProductResponse();

		try {
			productDao.registProduct(name, price, description);
			response.setResult(true);
		} catch (Exception ex) {
			response.setResult(false);
			ex.printStackTrace();
		}

		return response;
	}

	public GetProductListResponse getProductList(String name, BigDecimal price, String description,
			XMLGregorianCalendar createdAt, XMLGregorianCalendar updatedAt) {

		GetProductListResponse response = new GetProductListResponse();

		try {
			response.getProductList().addAll(productDao.getProducts(name, price, description, createdAt, updatedAt));
			response.setResult(true);
		} catch (Exception ex) {
			response.setResult(false);
			ex.printStackTrace();
		}

		return response;
	}

	public UpdateProductResponse updateProduct(int productId, String name, BigDecimal price, String description) {

		UpdateProductResponse response = new UpdateProductResponse();

		try {
			productDao.updateProduct(productId, name, price, description);
			response.setResult(true);
		} catch (Exception ex) {
			response.setResult(false);
			ex.printStackTrace();
		}

		return response;
	}

	public DeleteProductResponse deleteProduct(int productId) {

		DeleteProductResponse response = new DeleteProductResponse();

		try {
			productDao.deleteProduct(productId);
			response.setResult(true);
		} catch (Exception ex) {
			response.setResult(false);
			ex.printStackTrace();
		}

		return response;
	}
}
