package model;
import javax.xml.bind.annotation.*;
import lombok.*;
import model.abstracts.Entity;

@EqualsAndHashCode()
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@XmlRootElement(name = "genre")
@XmlAccessorType(XmlAccessType.FIELD)
public class Genre extends Entity {
    @XmlAttribute
    private String name;

    @Builder
    public Genre(long id, String name) {
        super(id);
        this.name = name;
    }
}
