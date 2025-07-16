package com.virtual.threads.mapper;

import com.virtual.threads.entity.Product;
import com.virtual.threads.model.HttpProductRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-16T21:54:34+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class DtoToProductMapperImpl implements DtoToProductMapper {

    @Override
    public Product dtoToProduct(HttpProductRequest httpProductRequest) {
        if ( httpProductRequest == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( httpProductRequest.getName() );
        product.setPrice( httpProductRequest.getPrice() );

        return product;
    }
}
