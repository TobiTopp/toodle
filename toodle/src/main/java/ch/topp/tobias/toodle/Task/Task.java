package ch.topp.tobias.toodle.Task;


import ch.topp.tobias.toodle.Tag.Tag;
import ch.topp.tobias.toodle.Topic.Topic;
import ch.topp.tobias.toodle.processor.Processor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    @NotNull
    private Date dueDate;

    @OneToMany(mappedBy = "processorId")
    private List<Processor> processorData;

    @OneToMany(mappedBy = "topicId")
    private List<Topic> topicData;















}
