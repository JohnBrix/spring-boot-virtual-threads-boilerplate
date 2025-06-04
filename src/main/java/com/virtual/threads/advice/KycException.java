package com.virtual.threads.advice;

import com.virtual.threads.model.HttpKycResponse;
import com.virtual.threads.model.HttpProductResponse;
import lombok.Getter;

/**
 * package com.virtual.threads.advice; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: KycException.java, v 0.1 2025-05-27 6:12 PM John Brix Pomoy Exp $$
 */
@Getter
public class KycException extends RuntimeException{
    private static final long serialVersionUID = 2L;

    //Transient excluded from Serializable.
    private final transient HttpKycResponse httpKycResponse;

    public KycException(String message ,HttpKycResponse response) {
        super(getResultMessage(message));
        this.httpKycResponse = response;
    }

    private static String getResultMessage(String response) {
        if (response != null) {
            return response;
        }
        return "KYC error occurred";
    }
}
