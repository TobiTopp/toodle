package ch.topp.tobias.toodle.Topic;

import ch.topp.tobias.toodle.processor.Processor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Topic {

    @Id
    @Size(max = 255)
    @NotEmpty
    private String topicName;










}
