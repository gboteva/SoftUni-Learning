package com.example.xml_2.models.DTO.toyotaCar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ToyotaCarRootDTO {
    @XmlElement(name = "car")
    private List<ToyotaCarDTO> cars;

    public List<ToyotaCarDTO> getCars() {
        return cars;
    }

    public void setCars(List<ToyotaCarDTO> cars) {
        this.cars = cars;
    }
}
