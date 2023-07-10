package com.example.xml.models.DTO.categoriesByProductCount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRootDTO {

    @XmlElement(name = "category")
    private List<CategoryWithProductInfoDTO> categories;

    public List<CategoryWithProductInfoDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryWithProductInfoDTO> categories) {
        this.categories = categories;
    }
}
