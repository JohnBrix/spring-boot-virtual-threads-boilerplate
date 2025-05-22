package com.virtual.threads.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * package com.virtual.threads.model; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: Result.java, v 0.1 2025-05-23 2:52 AM John Brix Pomoy Exp $$
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    private Integer resultCode;
    private String resultMessage;
    private String resultDescription;
    private Boolean resulStatus;
}
