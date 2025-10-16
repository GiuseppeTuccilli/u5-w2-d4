package giuseppetuccilli.u5_w2_d4.exeptions;

public class BadRequestExeption extends RuntimeException {
    public BadRequestExeption(String message) {
        super(message);
    }
}
