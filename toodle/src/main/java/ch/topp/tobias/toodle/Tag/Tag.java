package ch.topp.tobias.toodle.Tag;

import ch.topp.tobias.toodle.Topic.Topic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Tag {

    @Id
    @GeneratedValue
    @NotNull
    private Long tagId;

    @Size(max = 255)
    @NotEmpty
    @NotNull
    private String tagName;

    @Column(nullable = false)
    @Size(max = 255)
    @NotNull
    private String tagDescription;

    public Tag() {}

    public Tag(String tagName, String tagDescription) {
        this.tagName = tagName;
        this.tagDescription = tagDescription;
    }
}


