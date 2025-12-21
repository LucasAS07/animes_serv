package lr.system.anime_service.controller;

import lr.system.anime_service.domain.Producer;
import lr.system.anime_service.mapper.ProducerMapper;
import lr.system.anime_service.request.ProducerPostRequest;
import lr.system.anime_service.request.ProducerPutRequest;
import lr.system.anime_service.response.ProducerGetResponse;
import lr.system.anime_service.response.ProducerPostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("v1/producers")
public class ProducerController {

    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> listAll(@RequestParam(required = false) String name) {
        var produces = Producer.getProducers();
        var producers = MAPPER.toProducerGetResponseList(produces);

        if (name == null) return ResponseEntity.ok(producers);

        var response = producers.stream()
                .filter(producer -> producer.getName().equalsIgnoreCase(name))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        var producerGetResponse = Producer.getProducers()
                .stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst()
                .map(MAPPER::toProducerGetResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Producer not found"));

        return ResponseEntity.ok(producerGetResponse);
    }

    @PostMapping
    public ResponseEntity<ProducerPostResponse> salve(@RequestBody ProducerPostRequest producerPostRequest) {
        var producer = MAPPER.toProducer(producerPostRequest);
        var response = MAPPER.toProducerPostResponse(producer);

        Producer.getProducers().add(producer);


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var producerToDelete = Producer.getProducers()
                .stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Producer not found"));

        Producer.getProducers().remove(producerToDelete);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> delete(@RequestBody ProducerPutRequest request) {
        var producerToRemove = Producer.getProducers()
                .stream()
                .filter(producer -> producer.getId().equals(request.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Producer not found"));

        var producerUpdate = MAPPER.toProducer(request, producerToRemove.getCreateAt());
        Producer.getProducers().remove(producerToRemove);
        Producer.getProducers().add(producerUpdate);

        return ResponseEntity.noContent().build();
    }

}
