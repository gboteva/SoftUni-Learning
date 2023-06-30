package com.example.springautomapping.services;

import com.example.springautomapping.models.entities.Game;
import com.example.springautomapping.models.entities.Order;
import com.example.springautomapping.models.entities.ShoppingCart;
import com.example.springautomapping.models.entities.User;
import com.example.springautomapping.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final GameService gameService;

    private final UserService userService;


    public OrderServiceImpl(OrderRepository orderRepository, GameService gameService, UserService userService) {
        this.orderRepository = orderRepository;
        this.gameService = gameService;
        this.userService = userService;
    }

    @Override
    public void addItem(String gameTitle) {

        User loggedInUser = returnLoggedInUser();

        Game game = getGameFromDBAndCheckIfExist(gameTitle);

        if (loggedInUser.getShoppingCart() == null){
            loggedInUser.setShoppingCart(new ShoppingCart());
        }

       loggedInUser.getShoppingCart().getGameSet().add(game);


       System.out.println(gameTitle + " added to cart.");

    }

    @Override
    public void removeItem(String gameTitle) {

        User loggedInUser = returnLoggedInUser();

        ShoppingCart shoppingCart = loggedInUser.getShoppingCart();

        if (shoppingCart == null){
            throw new NullPointerException("ShoppingCart is empty!");
        }

        Game game = getGameFromShoppingCartIfExist(shoppingCart, gameTitle);

        shoppingCart.getGameSet().remove(game);

        System.out.println(gameTitle + " removed from cart.");
    }

    @Override
    public void buyItem() {
        User loggedInUser = returnLoggedInUser();

        ShoppingCart userShoppingCart = loggedInUser.getShoppingCart();

        if (userShoppingCart == null){
            throw new NullPointerException("Shopping cart doesn't exist! Please initialize it!");
        }

        if (userShoppingCart.getGameSet().isEmpty()){
            throw new IllegalArgumentException("Shopping cart is empty!");
        }

        userShoppingCart.getGameSet().forEach(game -> {
            try {
                checkIfGameIsAlreadyAddedToAccount(loggedInUser,game);
                loggedInUser.getGames().add(game);
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        });


        userService.saveEntity(loggedInUser);

        System.out.println("Successfully bought games:");
        loggedInUser.getShoppingCart()
                .getGameSet()
                .forEach(game -> System.out.println("  " + game.getTitle()));


        Order order = new Order(loggedInUser);
        order.setProducts(userShoppingCart.getGameSet());
        orderRepository.save(order);

        userShoppingCart.setGameSet(new HashSet<>());
    }

    private User returnLoggedInUser(){
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser == null){
            throw new IllegalStateException("Please login!");
        }

        return loggedInUser;
    }

    private void checkIfGameIsAlreadyAddedToAccount(User loggedInUser, Game game){

        if (loggedInUser.getGames().contains(game)){
            throw new IllegalArgumentException("This game already exist in your account");
        }

    }

    private Game getGameFromDBAndCheckIfExist(String gameTitle){
        Game game = gameService.getGameFromDB(gameTitle);
        if (game == null){
            throw new NullPointerException("The game doesn't exist in catalog!");
        }

        return game;
    }

    private Game getGameFromShoppingCartIfExist(ShoppingCart shoppingCart, String gameTitle) {
        Game gameFromCart = shoppingCart.getGameSet().stream().filter(game -> game.getTitle().equals(gameTitle))
                .findFirst()
                .orElse(null);

        if (gameFromCart == null){
            throw new NullPointerException("The game is not in yours shopping cart!");
        }

        return gameFromCart;
    }

}
