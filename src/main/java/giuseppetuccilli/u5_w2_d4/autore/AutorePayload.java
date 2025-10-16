package giuseppetuccilli.u5_w2_d4.autore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AutorePayload {
    @NotBlank(message = "il nome è obbligatorio")
    @Size(min = 2, max = 30, message = "il nome deve avere un lunghezza in caratteri compresa tra 2 e 30")
    private String nome;
    @NotBlank(message = "il nome è obbligatorio")
    @Size(min = 2, max = 30, message = "il cognome deve avere un lunghezza in caratteri compresa tra 2 e 30")
    private String cognome;
    @NotBlank(message = "L'email è obbligatoria!")
    @Email(message = "il formato dell'email non è corretto")
    private String email;
    private String dataNascita;
    private String avatar = "https://ui-avatars.com/api/?name=Mario+Rossi";
}
