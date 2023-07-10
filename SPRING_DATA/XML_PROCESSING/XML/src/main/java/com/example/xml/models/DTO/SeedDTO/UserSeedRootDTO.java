package com.example.xml.models.DTO.SeedDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedRootDTO {

    @XmlElement(name = "user")
    List<UserSeedDTO> users;

    public List<UserSeedDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserSeedDTO> users) {
        this.users = users;
    }
}
