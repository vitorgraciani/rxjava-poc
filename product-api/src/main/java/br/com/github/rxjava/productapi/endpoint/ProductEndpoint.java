package br.com.github.rxjava.productapi.endpoint;

import br.com.github.rxjava.productapi.endpoint.Resource.ProductRequest;
import br.com.github.rxjava.productapi.model.Category;
import br.com.github.rxjava.productapi.model.Product;
import br.com.github.rxjava.productapi.service.ProductService;
import io.reactivex.Observable;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/v1/product")
public class ProductEndpoint {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public Observable<ResponseEntity<Set<Product>>> getProducts(){
        Observable<Set<Product>> products = productService.getProducts();
        return products.map(ResponseEntity::ok);

    }

    @PostMapping()
    public Observable<ResponseEntity<Integer>> saveProduct(@Valid @RequestBody ProductRequest request, Errors errors){
        if(errors.hasErrors()){
            return Observable.just(new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST));
        }
        final Category category = new Category(request.getCategoryRequest().getName(), request.getCategoryRequest().getDescription());
        final Product product = new Product(request.getName(), request.getDescription(), request.getPrice(), category);
        return productService.saveProduct(product).map(ResponseEntity::ok)
                .onErrorResumeNext(Observable.just(new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST)));
    }

    @GetMapping("/{id}")
    public Observable<ResponseEntity<Product>> getProduct(@PathVariable Integer id){
        return productService.getProduct(id)
                .map(ResponseEntity::ok)
                .doOnError(throwable -> new NotFoundException("Produto nao encontrado."));
    }

}
