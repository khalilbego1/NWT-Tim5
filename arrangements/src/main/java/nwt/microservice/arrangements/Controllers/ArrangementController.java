package nwt.microservice.arrangements.Controllers;

import nwt.microservice.arrangements.Entities.Arrangement;
import nwt.microservice.arrangements.Repositories.ArrangementRepo;
import nwt.microservice.arrangements.Services.ArrangementsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArrangementController {

    @Autowired
    private final ArrangementRepo _arrangementRepo;

    ArrangementController(ArrangementRepo arrangementRepo){
        _arrangementRepo = arrangementRepo;
    }

    private ArrangementsService arrangementsService = new ArrangementsService();


//    @GetMapping("/arrangements")
    List<Arrangement> all() {
        return arrangementsService.findAll();
    }

    @GetMapping("/arrangements")
    public List<Arrangement> findAll() {
        try {
//            return getListFromIterator(_arrangementRepo.findAll());
            return arrangementsService.findAll();
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

    @PostMapping("/arrangement")
    Arrangement newArrangement(@RequestBody Arrangement newArrangement) {
        return arrangementsService.addArrangement(newArrangement);
    }

    @DeleteMapping("/arrangements/{id}")
    boolean removeArrangement(Integer arrangementId)
    {
        return arrangementsService.removeArrangement(arrangementId);
    }

    @PutMapping("/arrangements/{id}")
    Arrangement replaceEmployee(@RequestBody Arrangement newArrangement, @PathVariable Integer id) {
        return arrangementsService.upsertArrangement(newArrangement, id);
    }


}

