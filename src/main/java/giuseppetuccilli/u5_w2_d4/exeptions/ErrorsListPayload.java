package giuseppetuccilli.u5_w2_d4.exeptions;

import java.util.List;


public record ErrorsListPayload(String message, List<String> errorsList) {
}
