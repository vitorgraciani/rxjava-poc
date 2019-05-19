package br.com.github.rxjava.productapi.infrastructure.repository;

import br.com.github.rxjava.productapi.infrastructure.repository.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
}
