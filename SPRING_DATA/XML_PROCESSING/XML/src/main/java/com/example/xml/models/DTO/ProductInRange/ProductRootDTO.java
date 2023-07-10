package com.example.xml.models.DTO.ProductInRange;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductRootDTO {

    @XmlElement(name = "product")
    private List<ProductInRangeDTO> products;

    public List<ProductInRangeDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInRangeDTO> products) {
        this.products = products;
    }
}
