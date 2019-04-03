package nwt.microservice.arrangements.Services;

import nwt.microservice.arrangements.Entities.Arrangement;
import nwt.microservice.arrangements.Repositories.ArrangementRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.lang.Iterable;

public class ArrangementsService {

    @Autowired
    private ArrangementRepo _arrangementRepo;

    public ArrangementsService(){

    }

    public ArrangementsService(ArrangementRepo arrangementRepo) {
        _arrangementRepo = arrangementRepo;
    }

    public Arrangement addArrangement(Arrangement arrangement){
        return _arrangementRepo.save(arrangement);
    }

    public boolean removeArrangement(Integer id){
        try {
            _arrangementRepo.deleteById(id);
            return true;
        } catch (Exception handlerException) {
            System.out.println(handlerException.toString());
            return false;
        }
    }

    public Arrangement upsertArrangement(Arrangement newArrangement, Integer id){
        return _arrangementRepo.findById(id)
                .map(arrangement -> {
                    arrangement.additionalActivities = newArrangement.additionalActivities;
                    return _arrangementRepo.save(arrangement);
                })
                .orElseGet(() -> {
//                    newEmployee.setId(id);
                    return _arrangementRepo.save(newArrangement);
                });
    }

    public List<Arrangement> findAll() {
        try {
            return getListFromIterator(_arrangementRepo.findAll());
        } catch (Exception handlerException) {
            System.out.println(handlerException.toString());
            return null;
        }
    }

    private <T> List<T> getListFromIterator(Iterable<T> iterator)
    {

        // Create an empty list
        List<T> list = new ArrayList<>();

        // Add each element of iterator to the List
        iterator.forEach(list::add);

        // Return the List
        return list;
    }
}
