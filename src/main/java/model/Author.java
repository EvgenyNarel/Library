package model;
import javax.xml.bind.annotation.*;
import lombok.*;
import model.abstracts.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
@XmlRootElement (name="author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author extends Entity {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String surname;

    @Builder
    public Author(long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }
}