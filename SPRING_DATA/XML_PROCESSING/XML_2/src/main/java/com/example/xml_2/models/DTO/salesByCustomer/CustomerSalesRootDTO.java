package com.example.xml_2.models.DTO.salesByCustomer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSalesRootDTO {
    @XmlElement(name = "customer")
    private List<CustomerWithSalesDTO> customers;

    public List<CustomerWithSalesDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerWithSalesDTO> customers) {
        this.customers = customers;
    }
}
