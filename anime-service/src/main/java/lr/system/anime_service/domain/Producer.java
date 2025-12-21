package lr.system.anime_service.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producer {

    private Long id;
    private String name;
    private LocalDateTime createAt;
    private static List<Producer> producers = new ArrayList<>();
    static {
        var marvel = Producer.builder().id(1L).name("Marvel").createAt(LocalDateTime.now()).build();
        var dc = Producer.builder().id(1L).name("DC").createAt(LocalDateTime.now()).build();
        producers.addAll(List.of(marvel,dc));
    }

    public static List<Producer> getProducers() {
        return producers;
    }
}
