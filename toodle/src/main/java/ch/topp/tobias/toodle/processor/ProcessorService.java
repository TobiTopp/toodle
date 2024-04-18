package ch.topp.tobias.toodle.processor;


import ch.topp.tobias.toodle.base.MessageResponse;
import ch.topp.tobias.toodle.storage.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessorService {

    private final ProcessorRepository repository;

    public ProcessorService(ProcessorRepository repository) {
        this.repository = repository;
    }

    public List<Processor> getProcessors() {
        return repository.findByOrderByFirstName();
    }

    public Processor getProcessor(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Processor.class));
    }

    public Processor insertProcessor(Processor processor) {
        return repository.save(processor);
    }

    public Processor updateProcessor(Processor processor, Long id) {
        return repository.findById(id)
                .map(processorOrig -> {
                    processorOrig.setFirstName(processor.getFirstName()); // Aktualisiere den Vornamen
                    processorOrig.setSecondName(processor.getSecondName()); // Aktualisiere den Nachnamen
                    return repository.save(processorOrig); // Speichere die aktualisierten Daten
                })
                .orElseGet(() -> repository.save(processor)); // Falls der Processor nicht gefunden wird, füge ihn als neuen Eintrag hinzu
    }


    public MessageResponse deleteProcessor(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Processor " + id + " deleted");
    }
}
