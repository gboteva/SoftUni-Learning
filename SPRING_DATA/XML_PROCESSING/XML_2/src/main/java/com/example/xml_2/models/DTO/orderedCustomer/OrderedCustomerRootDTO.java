package com.example.xml_2.models.DTO.orderedCustomer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomerRootDTO {

    @XmlElement(name = "customer")
    private List<OrderCustomerDTO> customers;

    public List<OrderCustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<OrderCustomerDTO> customers) {
        this.customers = customers;
    }
}
