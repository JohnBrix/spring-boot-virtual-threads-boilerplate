
package com.virtual.threads.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


/**
 * package com.virtual.threads.model; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: HttpWireMockResponse.java, v 0.1 2025-06-4 9:53 AM John Brix Pomoy Exp $$
 */

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpKycResponse {
    private String description;
    private String message;
    private Integer status;
    private Boolean isSuccess;
}
