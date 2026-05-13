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
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public ProductDto findById(Long id) {
		
		Product product =  repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ProductDto(product);
		
	}

	
	@Transactional(readOnly = true)
	public Page<ProductDto> findAll(Pageable pageable) {
		
		Page<Product> result =  repository.findAll(pageable);
		return result.map(x -> new ProductDto(x));
		
	}
	
	@Transactional //não precisa do readOnly pq agra vamos salvar no banco
	public ProductDto insert(ProductDto dto) {
		
		Product entity = new Product();
		copyDtoToEntity(dto,entity);
		entity = repository.save(entity);
		return new ProductDto(entity);
	}
	
	
	@Transactional 
	public ProductDto update(Long id, ProductDto dto) {
		
		Product entity = repository.getReferenceById(id);
		copyDtoToEntity(dto,entity);
		entity = repository.save(entity);
		return new ProductDto(entity);
	}

	
	@Transactional
	public void delete(Long id) {
		
		repository.deleteById(id);
		
	}


	private void copyDtoToEntity(ProductDto dto, Product entity) {
		// TODO Auto-generated method stub
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		
	}
}
