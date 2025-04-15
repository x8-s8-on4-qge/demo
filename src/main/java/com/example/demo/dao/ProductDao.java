package com.example.demo.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.utils.DemoDateUtils;
import com.example.demo.webservice.Product;

@Component
public class ProductDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public Integer registProduct(String name, BigDecimal price, String description) {

		String sql = new String(
				"INSERT INTO product (name, price, description, created_at, updated_at) VALUES (:name, :price, :description, current_timestamp at time zone 'Asia/Tokyo', null)");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("price", price);
		params.put("description", description);

		return jdbcTemplate.update(sql, params);
	}

	public Product getProduct(int productId) throws DatatypeConfigurationException {

		String sql = new String("SELECT * FROM product WHERE product_id = :productId");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);

		Map<String, Object> result = jdbcTemplate.queryForMap(sql, params);

		Product product = new Product();
		try {
			product.setProductId((int)result.get("product_id"));
			product.setName((String)result.get("name"));
			product.setPrice((BigDecimal)result.get("price"));
			product.setDescription((String)result.get("description"));
			product.setCreatedAt(DemoDateUtils.convertDateToXMLGregorianCalendar((Timestamp)result.get("created_at")));
			product.setUpdatedAt(DemoDateUtils.convertDateToXMLGregorianCalendar((Timestamp)result.get("updated_at")));
		} catch (DatatypeConfigurationException ex) {
			throw ex;
		}

		return product;
	}

	public Product getProduct(String name) throws DatatypeConfigurationException {

		String sql = new String("SELECT * FROM product WHERE name = :name");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);

		Map<String, Object> result = jdbcTemplate.queryForMap(sql, params);

		Product product = new Product();
		try {
			product.setProductId((int)result.get("product_id"));
			product.setName((String)result.get("name"));
			product.setPrice((BigDecimal)result.get("price"));
			product.setDescription((String)result.get("description"));
			product.setCreatedAt(DemoDateUtils.convertDateToXMLGregorianCalendar((Timestamp)result.get("created_at")));
			product.setUpdatedAt(DemoDateUtils.convertDateToXMLGregorianCalendar((Timestamp)result.get("updated_at")));
		} catch (DatatypeConfigurationException ex) {
			throw ex;
		}

		return product;
	}

	public List<Product> getProducts(String name, BigDecimal price, String description, XMLGregorianCalendar createdAt,
			XMLGregorianCalendar updatedAt) {

		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		sql.append("SELECT * FROM product");

		if (!StringUtils.isEmpty(name) || price != null || !StringUtils.isEmpty(description) || createdAt != null
				|| updatedAt != null) {

			sql.append(" WHERE ");

			if (!StringUtils.isEmpty(name)) {
				sql.append("name LIKE :name AND ");
				params.put("name", name);
			}
			if (price != null) {
				sql.append("price = :price AND ");
				params.put("price", price);
			}
			if (!StringUtils.isEmpty(description)) {
				sql.append("description LIKE :description AND ");
				params.put("description", description);
			}
			if (createdAt != null) {
				sql.append("created_at = :createdAt AND ");
				params.put("createdAt", DemoDateUtils.convertXMLGregorianCalendarToTimestamp(createdAt));
			}
			if (updatedAt != null) {
				sql.append("updated_at = :updatedAt AND ");
				params.put("updatedAt", DemoDateUtils.convertXMLGregorianCalendarToTimestamp(updatedAt));
			}

			sql.setLength(sql.length() - 5);
		}

		sql.append(" ORDER BY product_id");

		return jdbcTemplate.query(sql.toString(), params, (rs, rowNum) -> {
			try {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getBigDecimal("price"));
				product.setDescription(rs.getString("description"));
				product.setCreatedAt(DemoDateUtils.convertDateToXMLGregorianCalendar(rs.getTimestamp("created_at")));
				product.setUpdatedAt(DemoDateUtils.convertDateToXMLGregorianCalendar(rs.getTimestamp("updated_at")));

				return product;
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		});
	}

	public Integer updateProduct(int productId, String name, BigDecimal price, String description) {

		String sql = new String(
				"UPDATE product SET name = :name, price = :price, description = :description, updated_at = current_timestamp at time zone 'Asia/Tokyo' WHERE product_id = :productId");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		params.put("name", name);
		params.put("price", price);
		params.put("description", description);

		return jdbcTemplate.update(sql, params);
	}

	public Integer deleteProduct(int productId) {

		String sql = new String("DELETE FROM product WHERE product_id = :productId");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);

		return jdbcTemplate.update(sql, params);
	}
}
