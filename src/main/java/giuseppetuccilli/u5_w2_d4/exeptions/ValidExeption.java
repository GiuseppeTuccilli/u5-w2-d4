package giuseppetuccilli.u5_w2_d4.exeptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidExeption extends RuntimeException {
    private List<String> errorsMessages;

    public ValidExeption(List<String> errorsMessage) {
        super("ci sono stati errori nella validazione");
        this.errorsMessages = errorsMessage;
    }
}
