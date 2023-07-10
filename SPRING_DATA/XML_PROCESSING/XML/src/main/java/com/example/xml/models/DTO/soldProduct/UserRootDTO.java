package com.example.xml.models.DTO.soldProduct;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRootDTO {
    @XmlElement(name = "user")
    List<UserWithSoldProductDTO> users;

    public List<UserWithSoldProductDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSoldProductDTO> users) {
        this.users = users;
    }
}
