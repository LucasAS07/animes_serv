package lr.system.anime_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Anime {

    private Long id;
    private String name;
    private static List<Anime> animes = new ArrayList<>();

    static {
        var aquaman = new Anime(1L,"Aquaman");
        var batman = new Anime(2L,"Batman");
        var superman = new Anime(3L,"Superman");
        var flash = new Anime(4L,"Flash");
        animes.addAll(List.of(aquaman,batman,superman,flash));
    }

    public static List<Anime> getAnimes() {
        return animes;
    }
}
