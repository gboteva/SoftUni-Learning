package com.example.spring_lab.services;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal money, Long ig);
    void transferMoney(BigDecimal money, Long id);
}
