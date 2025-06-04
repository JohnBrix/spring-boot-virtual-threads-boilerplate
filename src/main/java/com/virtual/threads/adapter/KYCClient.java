package com.virtual.threads.adapter;

import com.virtual.threads.advice.KycException;
import com.virtual.threads.model.HttpKycResponse;
import com.virtual.threads.model.HttpUserRequest;

/**
 * package com.virtual.threads.adapter; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: WiremockService.java, v 0.1 2025-06-04 8:59 PM John Brix Pomoy Exp $$
 */
public interface KYCClient {
    HttpKycResponse getKyc(HttpUserRequest httpUserRequest)throws KycException;
}
