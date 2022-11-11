package ee.secretagency.endofthegame.service;

import ee.secretagency.endofthegame.entity.Income;
import ee.secretagency.endofthegame.repository.IncomesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class IncomesService {

    private final IncomesRepository incomesRepository;

    public IncomesService(IncomesRepository incomesRepository) {
        this.incomesRepository = incomesRepository;
    }

    public List<Income> readAllIncomes() {
//        List<Income> result = repository.findAll();
        var incomesFromDb = incomesRepository.findAll();

        log.info("incomes from datasource: {}", incomesFromDb);
        return incomesFromDb;
    }

    public Income readIncomeById(Long id) {
        log.info("reading income with id: [{}]", id);
        Income income = null;
        try{
            income = incomesRepository.getOne(id);
            if(income == null){
                log.info("It's null");
            }else{
                log.info("It's not mull");
            }
            log.info("" + income);
            log.info("read income: [{}]", income);

        }catch(EntityNotFoundException e){
            log.warn("some unexpected exception has occurred");
            return null;
        }
        return income;
    }

    public Income  readIncomeByIdBetterWay(Long id){
        log.info("reading income with id: [{}]- better way", id);
        var maybeIncome = incomesRepository.findById(id);
        return maybeIncome.orElse(null);
    }


}
