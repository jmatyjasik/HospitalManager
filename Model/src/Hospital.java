import java.util.ArrayList;
import java.util.List;

public class Hospital {

    private List<Worker> allWorkers;

    public Hospital() {
        allWorkers = new ArrayList<>();
    }

    public void AddWorker(Worker worker){

        worker.Validate();

        if (allWorkers.stream().anyMatch(i->i.login == worker.login)){
            throw new Exception("Uzytkownik istneiej");
        }

        allWorkers.add(worker);
    }
}
