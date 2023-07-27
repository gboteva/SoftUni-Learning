package com.example.football.models.dto;

import com.example.football.models.entity.enums.Position;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedDto {
    @XmlElement(name = "first-name")
    @Size(min = 2)
    @NotBlank
    private String firstName;
    @XmlElement(name = "last-name")
    @Size(min = 2)
    @NotBlank
    private String lastName;
    @XmlElement(name = "email")
    @Email
    @NotBlank
    private String email;
    @XmlElement(name = "birth-date")
    @NotBlank
    private String birthDate;
    @XmlElement(name = "position")
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Position position;
    @XmlElement(name = "town")
    @NotNull
    private TownNameDto town;

    @XmlElement(name = "team")
    @NotNull
    private TeamNameDto team;

    @XmlElement(name = "stat")
    @NotNull
    private StatWithId stat;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TownNameDto getTown() {
        return town;
    }

    public void setTown(TownNameDto town) {
        this.town = town;
    }

    public StatWithId getStat() {
        return stat;
    }

    public void setStat(StatWithId stat) {
        this.stat = stat;
    }

    public TeamNameDto getTeam() {
        return team;
    }

    public void setTeam(TeamNameDto team) {
        this.team = team;
    }
}
