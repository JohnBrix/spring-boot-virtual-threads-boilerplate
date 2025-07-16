package com.virtual.threads.model;



import jakarta.validation.constraints.*;
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

    @NotNull(message = "UserId cannot be null")
    private Long userId;

    @NotNull(message = "PosTerminalId cannot be null")
    private Long posTerminalId;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, message = "Name must be at least 2 letters")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "1.0", message = "Price must be at least 1.0")
    private Double price;

    private Integer stock;
}
