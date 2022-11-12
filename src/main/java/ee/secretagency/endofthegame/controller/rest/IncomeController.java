package ee.secretagency.endofthegame.controller.rest;

import ee.secretagency.endofthegame.entity.Income;

import ee.secretagency.endofthegame.service.IncomesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class IncomeController {

    private final IncomesService incomesService;

    public IncomeController(IncomesService service) {
        this.incomesService = service;
    }

    // TODO: handle paging and sorting
    @GetMapping("/incomes")
    public List<Income> getAllIncomes() {
        log.info("getting all incomes");

        return incomesService.readAllIncomes();
    }

    @GetMapping("/incomes/{id}")
    public Income getIncomeById(@PathVariable("id") Long id) {
        log.info("trying to get income with id: [{}]", id);

        return incomesService.readIncomeByIdBetterWay(id);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/incomes/{id}")
    public void deleteIncomeById(@PathVariable Long id){
        log.info("trying to delete income with id: [{}]", id);
        incomesService.deleteIncomeWithIdBetter(id);
    }

    @PostMapping("/incomes")
    public ResponseEntity<Income> createNewIncome(@RequestBody Income income){
        log.info("creating new income: [{}]", income);

        Income newIncome =incomesService.createNewIncome(income);
        return ResponseEntity.created(URI.create("/incomes/" + newIncome.getId()))
                .body(newIncome);

    }

}
