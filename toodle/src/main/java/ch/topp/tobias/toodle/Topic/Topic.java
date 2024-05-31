package ch.topp.tobias.toodle.Topic;

import ch.topp.tobias.toodle.Tag.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Topic {


    @Id
    @GeneratedValue
    @NotNull
    private Long topicId;

    @Size(max = 255)
    @NotEmpty
    private String topicName;

    @Column(nullable = false)
    @Size(max = 255)
    private String topicDescription;


}