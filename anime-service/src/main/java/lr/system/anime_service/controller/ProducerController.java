package lr.system.anime_service.controller;

import lombok.RequiredArgsConstructor;
import lr.system.anime_service.mapper.ProducerMapper;
import lr.system.anime_service.request.ProducerPostRequest;
import lr.system.anime_service.request.ProducerPutRequest;
import lr.system.anime_service.response.ProducerGetResponse;
import lr.system.anime_service.response.ProducerPostResponse;
import lr.system.anime_service.servie.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/producers")
@RequiredArgsConstructor
public class ProducerController {

    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    private final ProducerService producerService;

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> listAll(@RequestParam(required = false) String name) {
        var produces = producerService.findAll(name);
        var producersGetResponse = MAPPER.toProducerGetResponseList(produces);

        return ResponseEntity.ok(producersGetResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        var producer = producerService.findByIdOrThrowNotFound(id);

        var producerGetResponse = MAPPER.toProducerGetResponse(producer);

        return ResponseEntity.ok(producerGetResponse);
    }

    @PostMapping
    public ResponseEntity<ProducerPostResponse> salve(@RequestBody ProducerPostRequest producerPostRequest) {
        var producer = MAPPER.toProducer(producerPostRequest);

        var producerSave = producerService.save(producer);

        var producerGetResponse = MAPPER.toProducerPostResponse(producerSave);

        return ResponseEntity.status(HttpStatus.CREATED).body(producerGetResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        producerService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProducerPutRequest request) {
        var producerToUpdate = MAPPER.toProducer(request);

        producerService.update(producerToUpdate);

        return ResponseEntity.noContent().build();
    }

}
