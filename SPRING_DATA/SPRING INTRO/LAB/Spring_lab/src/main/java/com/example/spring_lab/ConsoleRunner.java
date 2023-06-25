package com.example.spring_lab;

import com.example.spring_lab.models.Account;
import com.example.spring_lab.models.User;
import com.example.spring_lab.services.AccountService;
import com.example.spring_lab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = userService.getUserByUsername("Pesho");

        Set<Account> accounts = user.getAccounts();

        Long accountId = user.getAccounts().stream().findFirst().map(Account::getId).get();

        accountService.withdrawMoney(BigDecimal.valueOf(3000),accountId);

    }
}
