package com.virtual.threads.mapper;

import com.virtual.threads.entity.User;
import com.virtual.threads.model.HttpUserRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T17:23:37+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (GraalVM Community)"
)
@Component
public class DtoToUserMapperImpl implements DtoToUserMapper {

    @Override
    public User dtoToUser(HttpUserRequest httpUserRequest) {
        if ( httpUserRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( httpUserRequest.getUsername() );
        user.setPassword( httpUserRequest.getPassword() );

        return user;
    }
}
