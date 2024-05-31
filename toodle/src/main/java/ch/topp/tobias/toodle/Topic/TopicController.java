package ch.topp.tobias.toodle.Topic;


import ch.topp.tobias.toodle.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class TopicController {

    private final TopicService topicService;


    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("api/topics")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Topic>> getSortedTopics() {
        List<Topic> topics = topicService.getTopics();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @GetMapping("api/topic/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Topic> one(@PathVariable Long id) {
        Topic topic = topicService.getTopic(id);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @PostMapping("api/topic/create")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        Topic createdTopic = topicService.insertTopic(topic);
        return new ResponseEntity<>(createdTopic, HttpStatus.CREATED);
    }

    @PutMapping("api/topic/update/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Topic> updateTopic(@RequestBody Topic topic, @PathVariable Long id) {
        Topic updatedTopic = topicService.updateTopic(topic, id);
        return new ResponseEntity<>(updatedTopic, HttpStatus.OK);
    }

    @DeleteMapping("api/topic/delete/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
