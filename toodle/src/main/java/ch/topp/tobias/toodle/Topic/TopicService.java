package ch.topp.tobias.toodle.Topic;

import ch.topp.tobias.toodle.base.MessageResponse;
import ch.topp.tobias.toodle.storage.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<Topic> getTopics() {
        return repository.findByOrderByTopicName();
    }

    public Topic getTopic(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Topic.class));
    }

    public Topic insertTopic(Topic tag) {
        return repository.save(tag);
    }

    public Topic updateTopic(Topic topic, Long id) {
        return repository.findById(id)
                .map(topicOrig -> {
                    topicOrig.setTopicId(topic.getTopicId());
                    topicOrig.setTopicName(topic.getTopicName());
                    topicOrig.setTopicDescription(topic.getTopicDescription());
                    return repository.save(topicOrig);
                })
                .orElseGet(() -> repository.save(topic));
    }


    public MessageResponse deleteTopic(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Topic " + id + " deleted");
    }
}
