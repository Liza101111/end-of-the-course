package ee.secretagency.endofthegame.controller.rest;

import ee.secretagency.endofthegame.entity.Income;
import ee.secretagency.endofthegame.service.IncomesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class IncomesController {

    private final IncomesService incomesService;

    public IncomesController(IncomesService service) {
        this.incomesService = service;
    }

    // TODO: handle paging and sorting
    @GetMapping("/incomes")
    public List<Income> getAllIncomes() {
        log.info("getting all incomes");

        return incomesService.readAllIncomes();
    }

    @GetMapping("/incomes/{id}")
    public ResponseEntity<Income> getIncomeById(@PathVariable("id") Long id) {
        log.info("trying to get income with id: [{}]", id);
        //Income income = incomesService.readIncomeById(id);
        Income income = incomesService.readIncomeByIdBetterWay(id);
        boolean exists = income != null;

        if (exists) {
            return ResponseEntity.ok(income);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
