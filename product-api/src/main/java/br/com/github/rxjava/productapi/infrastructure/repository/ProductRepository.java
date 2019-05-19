package br.com.github.rxjava.productapi.infrastructure.repository;

import br.com.github.rxjava.productapi.infrastructure.repository.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
