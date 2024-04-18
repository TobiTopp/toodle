package ch.topp.tobias.toodle.Tag;

import ch.topp.tobias.toodle.base.MessageResponse;
import ch.topp.tobias.toodle.storage.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public List<Tag> getTags() {
        return repository.findByOrderByTagName();
    }

    public Tag getTag(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Tag.class));
    }

    public Tag insertTag(Tag tag) {
        return repository.save(tag);
    }

    public Tag updateTag(Tag tag, Long id) {
        return repository.findById(id)
                .map(tagOrig -> {
                    tagOrig.setTagId(tag.getTagId());
                    tagOrig.setTagName(tag.getTagName());
                    tagOrig.setDescription(tag.getDescription());
                    tagOrig.setTopic(tag.getTopic());
                    return repository.save(tagOrig);
                })
                .orElseGet(() -> repository.save(tag));
    }


    public MessageResponse deleteTag(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Tag " + id + " deleted");
    }
}
