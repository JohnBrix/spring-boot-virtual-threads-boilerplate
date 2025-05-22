package com.virtual.threads.mapper;

import com.virtual.threads.entity.User;
import com.virtual.threads.model.HttpUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * package com.virtual.threads.mapper; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: DtoToUserMapper.java, v 0.1 2025-05-23 2:30 AM John Brix Pomoy Exp $$
 */
@Mapper(componentModel = "spring")
public interface DtoToUserMapper {

    //Example: DTO id to userId Entity
    //@Mapping(source = "id",target = "userId")
    User dtoToUser(HttpUserRequest httpUserRequest);
}
