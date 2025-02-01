package andrew.volostnykh.dddemo.service.detail;

import andrew.volostnykh.dddemo.model.Detail;
import andrew.volostnykh.dddemo.model.DetailValueType;
import andrew.volostnykh.dddemo.model.dto.DetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DetailProcessor {
    private final DetailProcessorContext detailProcessorContext;

    public List<DetailDto> processDetails(Object entity, List<Detail> details) {
        return details.stream().map(detail -> {
            if (detail.getValueType() == DetailValueType.COMPUTABLE) {
                return processComputable(entity, detail, relatedDetailsFilter(detail, details));
            }

            return map(detail);
        }).toList();
    }

    public DetailDto processComputable(Object entity, Detail computable, List<Detail> relatedDetails) {
        String value = new ComputableDetailExecutor(
                computable,
                relatedDetails.stream().collect(Collectors.toMap(Detail::getCode, detail -> detail)),
                entity,
                detailProcessorContext
        ).getValue();

        return DetailDto.builder()
                .id(computable.getId())
                .name(computable.getName())
                .value(value)
                .code(computable.getCode())
                .chronoUnit(computable.getChronoUnit())
                .date(computable.getDate())
                .formula(computable.getFormula())
                .valueType(computable.getValueType())
                .build();
    }

    public DetailDto processPeriodic(Object entity, Detail detail) {
        return null;
    }

    private List<Detail> relatedDetailsFilter(Detail detail, List<Detail> details) {
        return details.stream()
                .filter(related -> !detail.getId().equals(related.getId()))
                .toList();
    }

    public DetailDto map(Detail detail) {
        return DetailDto.builder()
                .id(detail.getId())
                .name(detail.getName())
                .value(detail.getValue())
                .code(detail.getCode())
                .chronoUnit(detail.getChronoUnit())
                .date(detail.getDate())
                .formula(detail.getFormula())
                .valueType(detail.getValueType())
                .build();
    }


}
