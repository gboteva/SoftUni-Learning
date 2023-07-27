package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedDto {
    @NotBlank
    @Size(min = 21)
    private String caption;
    @NotNull
    private PostUserDto user;

    @NotNull
    private PostPictureDto picture;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public PostUserDto getUser() {
        return user;
    }

    public void setUser(PostUserDto user) {
        this.user = user;
    }

    public PostPictureDto getPicture() {
        return picture;
    }

    public void setPicture(PostPictureDto picture) {
        this.picture = picture;
    }
}
