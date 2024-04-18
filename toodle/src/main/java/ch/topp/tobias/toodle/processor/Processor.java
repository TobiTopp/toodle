package ch.topp.tobias.toodle.processor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Processor {

    @Id
    private Long processorId;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String firstName;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String secondName;
}