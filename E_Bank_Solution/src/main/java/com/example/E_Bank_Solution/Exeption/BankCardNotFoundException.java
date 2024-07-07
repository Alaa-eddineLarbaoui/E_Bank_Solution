package com.example.E_Bank_Solution.Exeption;

public class BankCardNotFoundException extends RuntimeException {
    public BankCardNotFoundException() {
        super("Bank card not found");
    }
}
