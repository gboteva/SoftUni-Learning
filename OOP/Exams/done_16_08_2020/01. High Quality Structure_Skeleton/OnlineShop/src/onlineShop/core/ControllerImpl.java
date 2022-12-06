package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Map<Integer, Computer> computers;
    private Map<Integer, Component> components;
    private Map<Integer, Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new LinkedHashMap<>();
        this.components = new LinkedHashMap<>();
        this.peripherals = new LinkedHashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if (computers.containsKey(id)){
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }

        Computer computer;
        if ("DesktopComputer".equals(computerType)){
            computer = new DesktopComputer(id, manufacturer, model, price);
        }else if ("Laptop".equals(computerType)){
            computer = new Laptop(id, manufacturer, model, price);
        }else {
            throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }

        this.computers.put(id, computer);

        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
       if (!computers.containsKey(computerId)){
           throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
       }

       if (peripherals.containsKey(id)){
           throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
       }

       Peripheral peripheral;
       switch (peripheralType){
           case "Headset":
               peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
               break;
           case "Keyboard":
               peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
               break;
           case "Monitor":
               peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
               break;
           case "Mouse":
               peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
               break;
           default: throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
       }

       Computer computer = computers.get(computerId);
       computer.addPeripheral(peripheral);
       this.peripherals.put(id, peripheral);

       return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        if (!computers.containsKey(computerId)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        Computer computer = computers.get(computerId);
        Peripheral toRemove = computer.removePeripheral(peripheralType);
        this.peripherals.remove(toRemove.getId());

        return String.format(REMOVED_PERIPHERAL, peripheralType, toRemove.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        if (!computers.containsKey(computerId)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        if (components.containsKey(id)){
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }

        Component component;
        switch (componentType){
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id,manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default: throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }

        Computer computer = computers.get(computerId);
        computer.addComponent(component);
        this.components.put(id, component);

        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        if (!computers.containsKey(computerId)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        Computer computer = computers.get(computerId);
        Component removed = computer.removeComponent(componentType);
        this.components.remove(removed.getId());

        return String.format(REMOVED_COMPONENT, componentType, removed.getId());
    }

    @Override
    public String buyComputer(int id) {
        if (!computers.containsKey(id)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        Computer removed = this.computers.remove(id);
        return removed.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        Comparator<Computer> performanceComparator = (c1,c2) -> Double.compare(c2.getOverallPerformance(), c1.getOverallPerformance());
        List<Computer> sortedByPerformance = this.computers.values().stream().sorted(performanceComparator).collect(Collectors.toList());
        List<Computer> inBudget = sortedByPerformance.stream()
                .filter(c->c.getPrice() <= budget)
                .collect(Collectors.toList());

        if (computers.isEmpty()  || inBudget.isEmpty() ){
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }


       Computer toBuy = sortedByPerformance.stream()
               .filter(c->c.getPrice() <= budget)
                .collect(Collectors.toList()).get(0);

        this.computers.remove(toBuy.getId());

        return toBuy.toString();
    }

    @Override
    public String getComputerData(int id) {
        if (!computers.containsKey(id)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        return this.computers.get(id).toString();
    }


}
