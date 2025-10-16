package giuseppetuccilli.u5_w2_d4.blog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlogPayload {

    @NotBlank(message = "la categoria è obbligatoria")
    private String categoria;
    @NotBlank(message = "il titolo è obbligatorio")
    private String titolo;
    @NotBlank(message = "il contenuto non può essere vuoto")
    @Size(min = 10, max = 2000, message = "il contenuto deve avere minimo 10 caratteri")
    private String contenuto;
    @NotNull(message = "il tempo di lettura è obbligatorio")
    private int tempoLettura;
    private String cover;
    private int idAutore;

    public BlogPayload(String categoria, String titolo, String contenuto, int tempoLettura, String cover, int idAut) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoLettura = tempoLettura;
        this.cover = cover;
        this.idAutore = idAut;

    }
}
