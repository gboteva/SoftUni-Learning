package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        String componentType = component.getClass().getSimpleName();

        if (isExistComponent(componentType)){
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT, componentType, getClass().getSimpleName(), super.getId()));
        }

        this.components.add(component);
    }

    private boolean isExistComponent(String componentType) {
       Component component = this.components.stream()
                .filter(c->c.getClass().getSimpleName().equals(componentType))
                .findFirst()
                .orElse(null);

       return component != null;
    }

    @Override
    public Component removeComponent(String componentType) {

        if (components.isEmpty() || !isExistComponent(componentType)){
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, componentType, getClass().getSimpleName(), super.getId() ));
        }

        Component componentToRemove =  this.components.stream()
                .filter(c->c.getClass().getSimpleName().equals(componentType))
                .findFirst()
                .orElse(null);

        components.remove(componentToRemove);

        return componentToRemove;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        String peripheralType = peripheral.getClass().getSimpleName();

        if (isExistPeripheral(peripheralType)){
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL, peripheralType, getClass().getSimpleName(), super.getId()));
        }

        this.peripherals.add(peripheral);
    }

    private boolean isExistPeripheral(String peripheralType) {
        Peripheral peripheral = this.peripherals.stream()
                .filter(c->c.getClass().getSimpleName().equals(peripheralType))
                .findFirst()
                .orElse(null);

        return peripheral != null;
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        if (peripherals.isEmpty() || !isExistPeripheral(peripheralType)){
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL, peripheralType, getClass().getSimpleName(), super.getId() ));
        }

        Peripheral peripheralToRemove =  this.peripherals.stream()
                .filter(c->c.getClass().getSimpleName().equals(peripheralType))
                .findFirst()
                .orElse(null);

        peripherals.remove(peripheralToRemove);

        return peripheralToRemove;
    }

    @Override
    public double getOverallPerformance() {
        if (this.components.isEmpty()){
            return super.getOverallPerformance();
        }else {
          double averagePerformanceOfComponents =  this.components.stream().mapToDouble(Component::getOverallPerformance).average().getAsDouble();
          return super.getOverallPerformance() + averagePerformanceOfComponents;
        }
    }

    @Override
    public double getPrice() {
        double computerPrice = super.getPrice();
        double componentsPrice = this.components.stream().mapToDouble(Component::getPrice).sum();
        double peripheralPrice = this.peripherals.stream().mapToDouble(Peripheral::getPrice).sum();

        return computerPrice + componentsPrice + peripheralPrice;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(PRODUCT_TO_STRING,
                getOverallPerformance(), getPrice(),
                getClass().getSimpleName(),
                getManufacturer(), getModel(), getId()));
        builder.append(System.lineSeparator());

        builder.append(" ").append(String.format(COMPUTER_COMPONENTS_TO_STRING, this.components.size())).append(System.lineSeparator());

        for (Component component : this.components) {
            builder.append("  ").append(component.toString()).append(System.lineSeparator());
        }

        double avgPerformance = this.peripherals.stream().mapToDouble(Peripheral::getOverallPerformance).average().orElse(0);
        builder.append(" ").append(String.format(COMPUTER_PERIPHERALS_TO_STRING, this.peripherals.size(), avgPerformance )).append(System.lineSeparator());

        for (Peripheral peripheral : peripherals) {
            builder.append("  ").append(peripheral.toString()).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
