public class Worker {

    public Worker(string login, string password, string name, string proffestion) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.proffestion = proffestion;
    }

    private string login;
    private string password;

    private string name;
    private string proffestion;

}


public class Doctor extends Worker {
    private Specialization specialization;
    private string number;

    public Doctor(string login, string password, string name, string proffestion, Specialization specialization, string number) {
        super(login, password, name, proffestion);
        this.specialization = specialization;
        this.number = number;
    }
}


