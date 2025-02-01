package andrew.volostnykh.dddemo.service;

import andrew.volostnykh.dddemo.model.DistributionContract;
import andrew.volostnykh.dddemo.model.Detail;
import andrew.volostnykh.dddemo.model.DetailEntityRelation;
import andrew.volostnykh.dddemo.model.dto.DetailDto;
import andrew.volostnykh.dddemo.model.dto.DistributionContractDto;
import andrew.volostnykh.dddemo.model.repository.DetailEntityRelationRepository;
import andrew.volostnykh.dddemo.model.repository.DetailRepository;
import andrew.volostnykh.dddemo.model.repository.DistributionContractRepository;
import andrew.volostnykh.dddemo.service.detail.DetailProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistributionContractService {

    private final DistributionContractRepository distributionContractRepository;
    private final DetailRepository detailRepository;
    private final DetailEntityRelationRepository detailEntityRelationRepository;
    private final DetailProcessor detailProcessor;

    public DistributionContractDto getByIdWithDetails(Long id) {
        DistributionContract distributionContract = distributionContractRepository.findById(id).orElseThrow();
        List<Detail> details = detailRepository.findByEntity(id, distributionContract.getEntityName());

        DistributionContractDto distributionContractDto = DistributionContractDto.builder()
                .name(distributionContract.getName())
                .price(distributionContract.getPrice())
                .depreciationRate(distributionContract.getDepreciationRate())
                .description(distributionContract.getDescription())
                .deadLine(distributionContract.getDeadLine())
                .delayFine(distributionContract.getDelayFine())
                .insuranceRate(distributionContract.getInsuranceRate())
                .startDate(distributionContract.getStartDate())
                .build();
        if (!details.isEmpty()) {
            distributionContractDto.setDetails(detailProcessor.processDetails(distributionContractDto, details));
        }

        return distributionContractDto;
    }

    @Transactional
    public DistributionContractDto create(DistributionContractDto distributionContractDto) {
        DistributionContract entity = DistributionContract.builder()
                .name(distributionContractDto.getName())
                .depreciationRate(distributionContractDto.getDepreciationRate())
                .price(distributionContractDto.getPrice())
                .delayFine(distributionContractDto.getDelayFine())
                .startDate(distributionContractDto.getStartDate())
                .deadLine(distributionContractDto.getDeadLine())
                .depreciationRate(distributionContractDto.getDepreciationRate())
                .insuranceRate(distributionContractDto.getInsuranceRate())
                .build();

        entity = distributionContractRepository.save(entity);

        distributionContractDto.setId(entity.getId());

        return distributionContractDto;
    }

    @Transactional
    public void addContractDetails(Long id, List<DetailDto> details) {
        details.forEach(detail -> {
            Detail entity = new Detail();
            entity.setDate(detail.getDate());
            entity.setValueType(detail.getValueType());
            entity.setCode(detail.getCode());
            entity.setFormula(detail.getFormula());
            entity.setChronoUnit(detail.getChronoUnit());
            entity.setName(detail.getName());
            entity.setValue(detail.getValue());

            entity = detailRepository.save(entity);

            DetailEntityRelation detailEntityRelation = new DetailEntityRelation();
            detailEntityRelation.setDetailId(entity.getId());
            detailEntityRelation.setEntityId(id);
            detailEntityRelation.setEntity(DistributionContract.DISTRIBUTION_CONTRACTS);

            detailEntityRelationRepository.save(detailEntityRelation);
        });

    }
}
