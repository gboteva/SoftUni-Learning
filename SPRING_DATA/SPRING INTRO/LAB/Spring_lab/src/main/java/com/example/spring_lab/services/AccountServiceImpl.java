package com.example.spring_lab.services;

import com.example.spring_lab.models.Account;
import com.example.spring_lab.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void withdrawMoney(BigDecimal money, Long id) {

        Account currentAccount = accountRepository.findAccountById(id);

        if (currentAccount == null) {
            System.out.println("Account doesn't exist");
            return;
        }

        if (currentAccount.getUser() == null){
            System.out.println("This account doesn't have owner");
            return;
        }

        if (money.doubleValue() < 0) {
            System.out.println("You try to withdraw negative value of money. Please enter valid value of money!");
            return;
        }

        if (currentAccount.getBalance().compareTo(money) < 0) {
            System.out.println("There is not enough money in you balance!");
            return;
        }

        currentAccount.setBalance(currentAccount.getBalance().subtract(money));
        accountRepository.save(currentAccount);

        System.out.println("You successfully withdraw money from your balance");
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Account currentAccount = accountRepository.findAccountById(id);

        currentAccount.setBalance(currentAccount.getBalance().add(money));
        System.out.println("You transfer was successful");

        accountRepository.save(currentAccount);

    }

}
