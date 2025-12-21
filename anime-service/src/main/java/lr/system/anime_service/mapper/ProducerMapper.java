package lr.system.anime_service.mapper;

import lr.system.anime_service.domain.Producer;
import lr.system.anime_service.request.ProducerPostRequest;
import lr.system.anime_service.request.ProducerPutRequest;
import lr.system.anime_service.response.ProducerGetResponse;
import lr.system.anime_service.response.ProducerPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ProducerMapper {

    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    @Mapping(target = "createAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id",
            expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(1, 1_000))")
    Producer toProducer(ProducerPostRequest postRequest);

    Producer toProducer(ProducerPutRequest putRequest, LocalDateTime createdAt);

    ProducerGetResponse toProducerGetResponse(Producer producer);

    ProducerPostResponse toProducerPostResponse(Producer producer);

    List<ProducerGetResponse> toProducerGetResponseList(List<Producer> producers);

}
