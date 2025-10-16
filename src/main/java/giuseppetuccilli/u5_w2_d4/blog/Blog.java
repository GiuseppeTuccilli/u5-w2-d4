package giuseppetuccilli.u5_w2_d4.blog;

import giuseppetuccilli.u5_w2_d4.autore.Autore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    private String categoria;
    private String titolo;
    private String cover = "https://picsum.photos/200/300";
    private String contenuto;
    private int tempoLettura;

    @ManyToOne
    @JoinColumn(name = "id_autore")
    private Autore autore;

    public Blog(String categoria, String titolo, String contenuto, int tempoLettura, Autore aut) {

        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoLettura = tempoLettura;
        this.autore = aut;

    }


    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                ", titolo='" + titolo + '\'' +
                ", cover='" + cover + '\'' +
                ", contenuto='" + contenuto + '\'' +
                ", tempoLettura=" + tempoLettura +
                '}';
    }
}
