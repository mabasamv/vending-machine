package com.vincent.assessment.config;

import com.vincent.assessment.service.IVendingMachine;
import com.vincent.assessment.service.VendingMachineImpl;

public class VendingMachineFactory {
    public static IVendingMachine createVendingMachine() { return new VendingMachineImpl(); }

}
