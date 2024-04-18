package ch.topp.tobias.toodle.Topic;

import ch.topp.tobias.toodle.Tag.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Topic {

    @Id
    @Size(max = 255)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @Size(max = 255)
    private String beschreibung; // Description in German

    @ManyToMany
    @JoinTable(
            name = "themenbereich_tag",
            joinColumns = @JoinColumn(name = "themenbereich_name"),
            inverseJoinColumns = @JoinColumn(name = "tag_name")
    )
    private List<Tag> tags;
}