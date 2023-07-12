package com.example.xml_2.models.DTO.salesWithDiscount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleWithDiscountRootDTO {
    @XmlElement(name = "sale")
    private List<SaleWithDiscountDTO> sales;

    public List<SaleWithDiscountDTO> getSales() {
        return sales;
    }

    public void setSales(List<SaleWithDiscountDTO> sales) {
        this.sales = sales;
    }
}
