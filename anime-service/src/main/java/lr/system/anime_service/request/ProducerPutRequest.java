package lr.system.anime_service.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProducerPutRequest {

    private Long id;
    private String name;

}
