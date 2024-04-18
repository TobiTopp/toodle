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
    @GeneratedValue
    private Long tagId;

    @Size(max = 255)
    @NotEmpty
    private String tagName;

    @Column(nullable = false)
    @Size(max = 255)
    private String tagDescription;


}
