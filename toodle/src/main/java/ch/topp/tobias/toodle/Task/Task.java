package ch.topp.tobias.toodle.Task;


import ch.topp.tobias.toodle.Topic.Topic;
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
    @NotEmpty String taskDescription;
    @Column(nullable = false)
    @NotEmpty
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "processorId", referencedColumnName = "processorId")
    private Processor processor;

    @ManyToOne
    @JoinColumn(name = "topicId", referencedColumnName = "topicId")
    private Topic topic;













}
