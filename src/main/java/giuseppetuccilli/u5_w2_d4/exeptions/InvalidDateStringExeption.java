package giuseppetuccilli.u5_w2_d4.exeptions;

public class InvalidDateStringExeption extends RuntimeException {
    public InvalidDateStringExeption(String data) {
        super(data + " - la stringa della data non è corretta");

    }
}
