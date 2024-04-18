package ch.topp.tobias.toodle.Tag;


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
public class TagController {

    private final TagService tagService;


    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("api/tags")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Tag>> getSortedTags() {
        List<Tag> tags = tagService.getTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("api/tag/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Tag> one(@PathVariable Long id) {
        Tag tag = tagService.getTag(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PostMapping("api/tag/create")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag createdTag = tagService.insertTag(tag);
        return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
    }

    @PutMapping("api/tag/update/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag, @PathVariable Long id) {
        Tag updatedTag = tagService.updateTag(tag, id);
        return new ResponseEntity<>(updatedTag, HttpStatus.OK);
    }

    @DeleteMapping("api/tag/delete/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
