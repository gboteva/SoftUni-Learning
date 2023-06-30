package com.example.springautomapping;

import com.example.springautomapping.models.dto.GameAddDTO;
import com.example.springautomapping.models.dto.UserDTO;
import com.example.springautomapping.services.GameService;
import com.example.springautomapping.services.OrderService;
import com.example.springautomapping.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class CommandRunnerImpl implements CommandLineRunner {

    private final UserService userService;
    private final GameService gameService;

    private final OrderService orderService;

    public CommandRunnerImpl(UserService userService, GameService gameService, OrderService orderService) {
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        System.out.println("Enter command with parameters or 'No' to break: ");
        String command = scanner.nextLine();

        while (!command.equals("No")) {
            String[] commandParts = command.split("\\|");

            String action = commandParts[0];

            try {
                switch (action) {
                    case "RegisterUser":
                        userService.registerUser(new UserDTO(commandParts[1],
                                commandParts[2], commandParts[3],
                                commandParts[4]));
                        break;
                    case "LoginUser":
                        userService.login(commandParts[1], commandParts[2]);
                        break;

                    case "Logout":
                        userService.logout();
                        break;

                    case "AddGame":
                        gameService.addGame(new GameAddDTO(commandParts[1],
                                new BigDecimal(commandParts[2]),
                                Double.parseDouble(commandParts[3]),
                                commandParts[4],
                                commandParts[5],
                                commandParts[6],
                                LocalDate.parse(commandParts[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                        break;

                    case "EditGame":
                        gameService.editGame(Long.parseLong(commandParts[1]),
                                Arrays.stream(commandParts).skip(2).collect(Collectors.toList()));
                        break;

                    case "DeleteGame":
                        gameService.deleteGame(Long.parseLong(commandParts[1]));
                        break;

                    case "AllGames":
                        gameService.printInfoForAllGames();
                        break;
                    case "DetailGame":
                        gameService.printDetailInfoAboutGameWith(commandParts[1]);
                        break;
                    case "OwnedGames":
                        userService.printGamesOfLoggedInUser();
                        break;
//                    case "BuyGame":
//                        gameService.buyGame(Long.parseLong(commandParts[1]));
//                        break;

                    case "AddItem":
                        orderService.addItem(commandParts[1]);
                        break;

                    case "RemoveItem":
                        orderService.removeItem(commandParts[1]);
                        break;

                    case "BuyItem":
                        orderService.buyItem();
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Enter command with parameters or 'No' to break: ");
            command = scanner.nextLine();
        }

    }
}
