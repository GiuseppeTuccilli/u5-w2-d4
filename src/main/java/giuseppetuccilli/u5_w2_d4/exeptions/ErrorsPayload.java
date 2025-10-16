package giuseppetuccilli.u5_w2_d4.exeptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class ErrorsPayload {
    private String message;
    private LocalDate data;
}
