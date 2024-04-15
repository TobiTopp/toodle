package ch.topp.tobias.toodle.Task;


import ch.topp.tobias.toodle.processor.Processor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue
    private long TaskId;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String taskName;

    @Column(nullable = false)
    @NotEmpty
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "processor_id", referencedColumnName = "processorId")
    private Processor processor;













}
