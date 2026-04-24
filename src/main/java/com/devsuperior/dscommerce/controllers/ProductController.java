package com.devsuperior.dscommerce.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscommerce.dto.ProductDto;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.ProductService;

@RestController
@RequestMapping(value  = "/products")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@GetMapping(value = "/{id}")
	public ProductDto findById(Long id) {
		return service.findById(id);
	}
}
