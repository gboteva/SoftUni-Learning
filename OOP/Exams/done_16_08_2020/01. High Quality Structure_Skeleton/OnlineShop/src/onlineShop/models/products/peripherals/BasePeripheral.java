package onlineShop.models.products.peripherals;

import onlineShop.models.products.BaseProduct;

import static onlineShop.common.constants.OutputMessages.PERIPHERAL_TO_STRING;


public abstract class BasePeripheral extends BaseProduct implements Peripheral {

    private String connectionType;

    protected BasePeripheral(int id, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        super(id, manufacturer, model, price, overallPerformance);
        this.connectionType = connectionType;
    }

    @Override
    public String getConnectionType() {
        return this.connectionType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString())
                .append(String.format(PERIPHERAL_TO_STRING, this.connectionType));

        return builder.toString().trim();
    }
}
