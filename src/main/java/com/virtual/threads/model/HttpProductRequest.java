package com.virtual.threads.model;

import lombok.Builder;
import lombok.Data;

/**
 * package com.virtual.threads.model; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: HttpProductResponse.java, v 0.1 2025-05-23 2:50 AM John Brix Pomoy Exp $$
 */
@Data
@Builder
public class HttpProductRequest {

    private Long productId;
    private Long userId;
    private Long posTerminalId;
    private String name;
    private Double price;
    private Integer stock;
}
