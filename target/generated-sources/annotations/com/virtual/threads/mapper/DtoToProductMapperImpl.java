package com.virtual.threads.mapper;

import com.virtual.threads.entity.Product;
import com.virtual.threads.model.HttpProductRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T17:23:38+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (GraalVM Community)"
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
