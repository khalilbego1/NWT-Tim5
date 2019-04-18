package nwt.microservice.arrangements.Services;

import nwt.microservice.arrangements.Repositories.UserArrangementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private final UserArrangementRepo userArrangementRepo;

    @Autowired
    public Receiver(UserArrangementRepo userArrangementRepo) {
        this.userArrangementRepo = userArrangementRepo;
    }

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        processMessage(message);
    }

    void processMessage(String message) {
        String[] niz = message.split(";");
        if (niz[1].equals("delete")) {
            userArrangementRepo.deleteByUserId(Integer.parseInt(niz[0]));
        }
    }
}
