package lr.system.anime_service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AnimePostResponse {

    private Long id;
    private String name;

}
