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
    private final ArrangementsService _arrangementService;

    ArrangementController(ArrangementsService arrangementsService){
        _arrangementService = arrangementsService;
    }


//    @GetMapping("/arrangements")
    List<Arrangement> all() {
        return _arrangementService.findAll();
    }

    @GetMapping("/arrangements")
    public List<Arrangement> findAll() {
        try {
//            return getListFromIterator(_arrangementRepo.findAll());
            return _arrangementService.findAll();
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
        return _arrangementService.addArrangement(newArrangement);
    }

    @DeleteMapping("/arrangements/{id}")
    boolean removeArrangement(Integer arrangementId)
    {
        return _arrangementService.removeArrangement(arrangementId);
    }

    @PutMapping("/arrangements/{id}")
    Arrangement replaceEmployee(@RequestBody Arrangement newArrangement, @PathVariable Integer id) {
        return _arrangementService.upsertArrangement(newArrangement, id);
    }


}

