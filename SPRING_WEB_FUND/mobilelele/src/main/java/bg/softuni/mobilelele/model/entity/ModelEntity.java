package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.enums.Category;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(length = 512, name = "image_url")
    private String imageUrl;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime modified;
    @ManyToOne
    private BrandEntity brand;
}
