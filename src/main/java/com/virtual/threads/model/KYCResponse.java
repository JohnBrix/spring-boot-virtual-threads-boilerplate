package com.virtual.threads.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * package com.virtual.threads.model; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: KYCResponse.java, v 0.1 2025-07-14 10:31 PM John Brix Pomoy Exp $$
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KYCResponse {
    private String description;
    private String message;
    private Integer status;
    private Boolean isSuccess;
}
