package com.vincent.assessment.config;

import com.vincent.assessment.service.IVendingMachineService;
import com.vincent.assessment.service.VendingMachineService;

public class VendingMachineFactory {
    public static IVendingMachineService createVendingMachine() { return new VendingMachineService(); }

}
