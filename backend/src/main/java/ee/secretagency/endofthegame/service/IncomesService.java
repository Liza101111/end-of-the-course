package ee.secretagency.endofthegame.service;

import ee.secretagency.endofthegame.entity.Income;
import ee.secretagency.endofthegame.exception.IncomeNotFoundException;
import ee.secretagency.endofthegame.repository.IncomesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Supplier;

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
       /* return maybeIncome.orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new EntityNotFoundException("No entity with id: [{%id}]".formatted(id));
            }
        });*/
        return maybeIncome.orElseThrow(() -> new IncomeNotFoundException("No entity with id: [{%id}]"));
    }


    public void deleteIncomeWithId(Long id) {
        log.info("deleting income with id: [{}]", id);
        try{
            incomesRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Try to delete non existent income", e);
            throw new IncomeNotFoundException("No existing income", e);
        }
    }

    @Transactional
    public void deleteIncomeWithIdBetter(Long id){
        log.info("deleting income with id: [{}]", id);
        if(incomesRepository.existsById(id)){
            incomesRepository.deleteById(id);
        } else {
            throw new IncomeNotFoundException("No existing income with id: [%d]".formatted(id));
        }
    }
    //TODO: fix id from the income
    public Income createNewIncome(Income income) {
        log.info("object before saving: [{}]", income);
        if(income.getTimestamp() == null){
            income.setTimestamp(ZonedDateTime.now());
        }
        Income saved = incomesRepository.save(income);
        log.info("object after saving: [{}]", saved);
        log.info("input income after saving: [{}]", income);

        return saved;
    }
}
