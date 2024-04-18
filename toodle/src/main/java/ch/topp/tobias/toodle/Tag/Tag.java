package ch.topp.tobias.toodle.Tag;

import ch.topp.tobias.toodle.Topic.Topic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Tag {

    @Id
    @Size(max = 255)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @Size(max = 255)
    private String description;

    @ManyToMany(mappedBy = "tags")
    private List<Topic> themenbereiche;
}