package exam.model.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class CustomerSeedDto {
    @Expose
    @Length(min = 2)
    private String firstName;
    @Expose
    @Length(min = 2)
    private String lastName;
    @Expose
    @Email
    private  String email;
    @Expose
    private  String registeredOn;
    @Expose
    private TownWithNameGsonDTO town;

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

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }

    public TownWithNameGsonDTO getTown() {
        return town;
    }

    public void setTown(TownWithNameGsonDTO town) {
        this.town = town;
    }
}
