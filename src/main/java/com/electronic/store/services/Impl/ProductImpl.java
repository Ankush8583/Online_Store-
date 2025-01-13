package com.electronic.store.services.Impl;

import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.ProductDto;
import com.electronic.store.entities.Categories;
import com.electronic.store.entities.Product;
import com.electronic.store.exceptions.ResourceNotFoundException;
import com.electronic.store.helper.Helper;
import com.electronic.store.repositories.CategoriesRepository;
import com.electronic.store.repositories.ProductRepository;
import com.electronic.store.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class ProductImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private CategoriesRepository categoriesRepository;
    @Override
    public ProductDto create(ProductDto productDto) {

        String productId = UUID.randomUUID().toString();
         productDto.setProductId(productId);

       Product product =  modelMapper.map(productDto, Product.class);
        Product savedProduct=productRepository.save(product);

        return modelMapper.map(savedProduct,ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {

        Product product=productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Resource not found with given id !!"));
        product.setTitle(productDto.getTitle());
        product.setLive(productDto.isLive());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setStock(productDto.isStock());
        product.setAddedDate(LocalDateTime.now());


       Product updatedProduct= productRepository.save(product);

        return modelMapper.map(updatedProduct,ProductDto.class);
    }

    @Override
    public void delete(String productId) {

       Product product =productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Resource not found with given Id !!"));
        productRepository.delete(product);

    }

    @Override
    public ProductDto get(String productId) {

       Product product= productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Resource not found with given id !!"));

        return modelMapper.map(product,ProductDto.class);
    }



    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page page = productRepository.findAll(pageable);
        PageableResponse pageableResponse = Helper.getPageableResponse(page, ProductDto.class);

        return pageableResponse;
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> page = productRepository.findByLiveTrue(pageable);
        PageableResponse pageableResponse = Helper.getPageableResponse(page, ProductDto.class);

        return pageableResponse;
    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> page = productRepository.findByTitleContaining(subTitle,pageable);
        PageableResponse pageableResponse = Helper.getPageableResponse(page, ProductDto.class);

        return pageableResponse;
    }

    @Override
    public ProductDto createWithCategories(ProductDto productDto, String categoriesId) {

        //fetch the cartegories
        Categories categories=categoriesRepository.findById(categoriesId).orElseThrow(() ->new ResourceNotFoundException("category not found with given id !!"));


        String productId = UUID.randomUUID().toString();
        productDto.setProductId(productId);
        Product product =  modelMapper.map(productDto, Product.class);
        product.setCategories(categories);
        Product savedProduct=productRepository.save(product);

        return modelMapper.map(savedProduct,ProductDto.class);
    }

    @Override
    public ProductDto updateCategories(String productId, String categoriesId) {

        //Fetch product

       Product product= productRepository.findById(productId).orElseThrow(() ->new ResourceNotFoundException("Product not found with given id !!"));
       Categories categories = categoriesRepository.findById(categoriesId).orElseThrow(() -> new ResourceNotFoundException("Categories not found with given id !!"));
        product.setCategories(categories);
        Product saveProduct =productRepository.save(product);

        return modelMapper.map(saveProduct,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllCategories(String categoriesID,int pageNumber,int pageSize) {

        Categories categories=categoriesRepository.findById(categoriesID).orElseThrow(() -> new ResourceNotFoundException("Categories not found with given id !!"));

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page page=productRepository.findByCategories(categories,pageable);

        return Helper.getPageableResponse(page,ProductDto.class);
    }


}
