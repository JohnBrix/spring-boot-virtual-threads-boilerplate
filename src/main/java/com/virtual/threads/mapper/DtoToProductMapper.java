package com.virtual.threads.mapper;

import com.virtual.threads.entity.Product;
import com.virtual.threads.model.HttpProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * package com.virtual.threads.mapper; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: DtoToProductMapper.java, v 0.1 2025-05-23 3:12 AM John Brix Pomoy Exp $$
 */
@Mapper(componentModel = "spring")
public interface DtoToProductMapper {

    Product dtoToProduct(HttpProductRequest httpProductRequest);
}
