package ch.topp.tobias.toodle;


import ch.topp.tobias.toodle.Tag.Tag;
import ch.topp.tobias.toodle.Tag.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    void insertTag() {
        Tag objGeo = this.tagRepository.save(new Tag("Geometrie", "Das ist ein Tag für Geometrie"));
        Assertions.assertNotNull(objGeo.getTagId());
        Tag objTrigo = this.tagRepository.save(new Tag("Trigonometrie", "Das ist ein Tag für Trigonometrie"));
        Assertions.assertNotNull(objTrigo.getTagId());
    }
}
