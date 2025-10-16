package giuseppetuccilli.u5_w2_d4.autore;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "autori")
@Getter
@Setter
@NoArgsConstructor
public class Autore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
    private String avatar = "https://ui-avatars.com/api/?name=Mario+Rossi";

    public Autore(LocalDate dataNascita, String email, String cognome, String nome) {

        this.dataNascita = dataNascita;
        this.email = email;
        this.cognome = cognome;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Autire{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", dataNascita='" + dataNascita + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
