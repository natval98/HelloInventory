package hello.inven.helloinven.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "category")
//@JsonIgnoreProperties({"categoryItems"})
public class Category extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @Column(name = "category_name", nullable = false)
    private String name;

    @Column(name = "category_description")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // cascade type remove (?)

//    https://stackoverflow.com/questions/31465440/recursive-json-view-of-an-entity-with-one-to-many-relationship-in-rest-controll
//    agar tidak keluar rekursif, ketika item panggil category, category panggil item, dst
//    @JsonIgnoreProperties
//    https://www.concretepage.com/jackson-api/jackson-jsonignore-jsonignoreproperties-and-jsonignoretype
//    @JsonProperty("categoryItems")
    @JsonIgnore
//    https://softwareengineering.stackexchange.com/questions/300115/best-way-to-deal-with-hibernate-1-many-relationship-over-rest-json-service
//    @JsonIgnoreProperties(value = "category", allowSetters = true)
    private List<Item> items;

    public Category(String name, String description, List<Item> items) {
        this.name = name;
        this.description = description;
        this.items = items;
    }

}
