package lr.system.anime_service.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnimePostRequest {

    private String name;

}
