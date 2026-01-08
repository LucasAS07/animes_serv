package lr.system.anime_service.controller;

import lombok.RequiredArgsConstructor;
import lr.system.anime_service.mapper.AnimeMapper;
import lr.system.anime_service.request.AnimePostRequest;
import lr.system.anime_service.request.AnimePutRequest;
import lr.system.anime_service.response.AnimeGetResponse;
import lr.system.anime_service.response.AnimePostResponse;
import lr.system.anime_service.servie.AnimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v1/animes")
@RequiredArgsConstructor
public class AnimeController {

    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAll(@RequestParam(required = false) String name) {
        var animes = animeService.findAll(name);
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);

        return ResponseEntity.ok(animeGetResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        var anime = animeService.findByIdOrThrowNotFound(id);
        var animeGetResponse = MAPPER.toAnimeGetResponse(anime);

        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> salve(@RequestBody AnimePostRequest request) {
        var anime = MAPPER.toAnime(request);

        var animeSave = animeService.save(anime);

        var animeGetResponse = MAPPER.toAnimePostResponse(animeSave);

        return ResponseEntity.status(HttpStatus.CREATED).body(animeGetResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animeService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        var animeUpdate = MAPPER.toAnime(request);

        animeService.update(animeUpdate);

        return ResponseEntity.noContent().build();
    }

}
