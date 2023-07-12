package com.example.xml_2.models.DTO.carsWithParts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsRootDTO {
    @XmlElement(name = "car")
    List<CarWithParts> cars;

    public List<CarWithParts> getCars() {
        return cars;
    }

    public void setCars(List<CarWithParts> cars) {
        this.cars = cars;
    }
}
