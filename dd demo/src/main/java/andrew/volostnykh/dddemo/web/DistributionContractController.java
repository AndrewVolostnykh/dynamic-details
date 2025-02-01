package andrew.volostnykh.dddemo.web;

import andrew.volostnykh.dddemo.model.dto.DetailDto;
import andrew.volostnykh.dddemo.model.dto.DistributionContractDto;
import andrew.volostnykh.dddemo.service.DistributionContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/distribution-contracts")
@RequiredArgsConstructor
public class DistributionContractController {

    private final DistributionContractService distributionContractService;

    @GetMapping("/{id}")
    public DistributionContractDto getById(@PathVariable Long id) {
        return distributionContractService.getByIdWithDetails(id);
    }

    @PostMapping
    public DistributionContractDto create(@RequestBody final DistributionContractDto distributionContractDto) {
        return distributionContractService.create(distributionContractDto);
    }

    @PostMapping("/{id}")
    public void addContractDetails(@PathVariable Long id, @RequestBody List<DetailDto> details) {
        distributionContractService.addContractDetails(id, details);
    }
}
