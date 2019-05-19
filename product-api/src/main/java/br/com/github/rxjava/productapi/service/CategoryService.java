package br.com.github.rxjava.productapi.service;

import br.com.github.rxjava.productapi.infrastructure.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void getCategory(){

    }
}
