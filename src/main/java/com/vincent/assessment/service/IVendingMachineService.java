package com.vincent.assessment.service;

import com.vincent.assessment.model.PurchaseRequest;
import com.vincent.assessment.model.PurchaseResponse;

public interface IVendingMachineService {

    PurchaseResponse purchase(final PurchaseRequest request);
}
