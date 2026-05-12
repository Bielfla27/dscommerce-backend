package com.devsuperior.dscommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.ProductDto;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public ProductDto findById(Long id) {
		
		Product product =  repository.findById(1L).get();
		return new ProductDto(product);
		
	}

	
	@Transactional(readOnly = true)
	public Page<ProductDto> findAll(Pageable pageable) {
		
		Page<Product> result =  repository.findAll(pageable);
		return result.map(x -> new ProductDto(x));
		
	}
	
	@Transactional //não precisa do readOnly pq agra vamos salvar no banco
	public ProductDto insert(ProductDto dto) {
		
		Product entiy = new Product();
		entiy.setName(dto.getName());
		entiy.setDescription(dto.getDescription());
		entiy.setPrice(dto.getPrice());
		entiy.setImgUrl(dto.getImgUrl());
		
		entiy = repository.save(entiy);
		
		return new ProductDto(entiy);
	}
}
