package nwt.microservice.arrangements.Services;

import nwt.microservice.arrangements.Repositories.ArrangementRepo;
import nwt.microservice.arrangements.Repositories.UserArrangementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private final UserArrangementRepo userArrangementRepo;
    private final ArrangementRepo arrangementRepo;

    @Autowired
    public Receiver(UserArrangementRepo userArrangementRepo, ArrangementRepo arrangementRepo) {
        this.userArrangementRepo = userArrangementRepo;
        this.arrangementRepo = arrangementRepo;
    }

    public void receiveMessage1(String message) {
        System.out.println("Received <" + message + ">");
        processMessage1(message);
    }

    public void receiveMessage2(String message) {
        System.out.println("Received <" + message + ">");
    }


    void processMessage1(String message) {
        String[] niz = message.split(";");
        if (niz[1].equals("delete")) {
            userArrangementRepo.deleteByUserId(Integer.parseInt(niz[0]));
        }
    }
}
