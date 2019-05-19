package br.com.github.rxjava.productapi.service;

import br.com.github.rxjava.productapi.infrastructure.repository.ProductRepository;
import br.com.github.rxjava.productapi.infrastructure.repository.entity.CategoryEntity;
import br.com.github.rxjava.productapi.infrastructure.repository.entity.ProductEntity;
import br.com.github.rxjava.productapi.model.Category;
import br.com.github.rxjava.productapi.model.Product;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    public Observable<Set<Product>> getProducts(){
        Observable<Iterable<ProductEntity>> observableProduct =
                Observable.defer(() -> Observable.just(productRepository.findAll()));

        return observableProduct.subscribeOn(Schedulers.io()).flatMap( p ->{
            Set<Product> products = new HashSet<>();
            Iterator<ProductEntity> itr = p.iterator();
            while(itr.hasNext()){
                ProductEntity entity = itr.next();
                Category category = new Category(entity.getCategory().getName(), entity.getCategory().getDescription());
                products.add(new Product(entity.getName(), entity.getDescription(), entity.getPrice(), category));
            }
            return Observable.just(products);
        }).onErrorResumeNext(Observable.just(new HashSet<Product>()));

    }

    public Observable<Integer> saveProduct(final Product product){
        Observable<Product> obs = Observable.defer(() -> Observable.just(product));

        return obs.subscribeOn(Schedulers.io()).flatMap(prd ->{
            final CategoryEntity categoryEntity = new CategoryEntity(prd.getCategory().getName(), prd.getCategory().getDescription());
            final ProductEntity productEntity = new ProductEntity(prd.getName(), prd.getDescription(), prd.getPrice(), categoryEntity);
            final Integer id = productRepository.save(productEntity).getId();
            return Observable.just(id);
        }).onErrorResumeNext(Observable.empty());
    }

    public Observable<Product> getProduct(Integer id) {
        Observable<Integer> obs = Observable.defer(() -> Observable.just(id));
        return obs.subscribeOn(Schedulers.io()).flatMap(productId ->{
            ProductEntity product = productRepository.findById(productId).get();
            Category category = new Category(product.getCategory().getName(), product.getCategory().getDescription());
            category.setId(product.getCategory().getId());
            Product prd = new Product(product.getName(), product.getDescription(), product.getPrice(), category);
            return Observable.just(prd);
        }).onErrorResumeNext(Observable.empty());
    }
}
