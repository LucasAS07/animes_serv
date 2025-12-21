package lr.system.anime_service.controller;

import lr.system.anime_service.domain.Anime;
import lr.system.anime_service.mapper.AnimeMapper;
import lr.system.anime_service.request.AnimePostRequest;
import lr.system.anime_service.request.AnimePutRequest;
import lr.system.anime_service.response.AnimeGetResponse;
import lr.system.anime_service.response.AnimePostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("v1/animes")
public class AnimeController {

    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAll(@RequestParam(required = false) String name) {

        var animes = Anime.getAnimes();
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);
        if (name == null) return ResponseEntity.ok(animeGetResponseList);

        var response = animeGetResponseList.stream()
                .filter(anime -> anime
                        .getName()
                        .equalsIgnoreCase(name))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {


        var animeGetResponse = Anime.getAnimes().stream()
                .filter(anime -> anime.getId()
                        .equals(id))
                .findFirst()
                .map(MAPPER::toAnimeGetResponse)
                .orElse(null);

        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> salve(@RequestBody AnimePostRequest request) {
        var anime = MAPPER.toAnime(request);

        Anime.getAnimes().add(anime);

        var response = MAPPER.toAnimePostResponse(anime);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var animeRemove = Anime.getAnimes().stream()
                .filter(anime -> anime.getId()
                        .equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Anime.getAnimes().remove(animeRemove);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        var animeToRemove = Anime.getAnimes().stream()
                .filter(anime -> anime.getId()
                        .equals(request.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var animeUpdate = MAPPER.toAnime(request);

        Anime.getAnimes().remove(animeToRemove);
        Anime.getAnimes().add(animeUpdate);

        return ResponseEntity.noContent().build();
    }

}
