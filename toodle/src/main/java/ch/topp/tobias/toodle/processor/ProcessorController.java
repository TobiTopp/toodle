package ch.topp.tobias.toodle.processor;


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
public class ProcessorController {

    private final ProcessorService processorService;


    public ProcessorController(ProcessorService processorService) {
        this.processorService = processorService;
    }

    @GetMapping("api/processors")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Processor>> getSortedProcessors() {
        List<Processor> processors = processorService.getProcessors();
        return new ResponseEntity<>(processors, HttpStatus.OK);
    }

    @GetMapping("api/processor/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Processor> one(@PathVariable Long id) {
        Processor processor = processorService.getProcessor(id);
        return new ResponseEntity<>(processor, HttpStatus.OK);
    }

    @PostMapping("api/processor/create")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Processor> createProcessor(@RequestBody Processor processor) {
        Processor createdProcessor = processorService.insertProcessor(processor);
        return new ResponseEntity<>(createdProcessor, HttpStatus.CREATED);
    }

    @PutMapping("api/processor/update/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Processor> updateProcessor(@RequestBody Processor processor, @PathVariable Long id) {
        Processor updatedProcessor = processorService.updateProcessor(processor, id);
        return new ResponseEntity<>(updatedProcessor, HttpStatus.OK);
    }

    @DeleteMapping("api/processor/delete/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Void> deleteProcessor(@PathVariable Long id) {
        processorService.deleteProcessor(id);
        return ResponseEntity.noContent().build();
    }
}
