package lr.system.anime_service.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AnimePutRequest {

    private Long id;
    private String name;

}
