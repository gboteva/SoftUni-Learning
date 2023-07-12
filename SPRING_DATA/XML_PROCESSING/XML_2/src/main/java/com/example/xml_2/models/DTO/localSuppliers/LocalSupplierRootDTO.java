package com.example.xml_2.models.DTO.localSuppliers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierRootDTO {

    @XmlElement(name = "supplier")
    private List<LocalSupplier> suppliers;

    public List<LocalSupplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<LocalSupplier> suppliers) {
        this.suppliers = suppliers;
    }
}
