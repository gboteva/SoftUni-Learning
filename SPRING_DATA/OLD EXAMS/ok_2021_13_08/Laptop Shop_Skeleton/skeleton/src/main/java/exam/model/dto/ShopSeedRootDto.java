package exam.model.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopSeedRootDto {
    @XmlElement(name = "shop")
    private List<ShopSeedDTO> shops;

    public List<ShopSeedDTO> getShops() {
        return shops;
    }

    public void setShops(List<ShopSeedDTO> shops) {
        this.shops = shops;
    }
}
