package lr.system.anime_service.mapper;

import lr.system.anime_service.domain.Anime;
import lr.system.anime_service.request.AnimePostRequest;
import lr.system.anime_service.request.AnimePutRequest;
import lr.system.anime_service.response.AnimeGetResponse;
import lr.system.anime_service.response.AnimePostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnimeMapper {

    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    @Mapping(target = "id",
            expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(1, 1_000))")
    Anime toAnime(AnimePostRequest postRequest);

    Anime toAnime(AnimePutRequest putRequest);

    AnimePostResponse toAnimePostResponse(Anime anime);

    AnimeGetResponse toAnimeGetResponse(Anime anime);

    List<AnimeGetResponse> toAnimeGetResponseList(List<Anime> animes);

}
