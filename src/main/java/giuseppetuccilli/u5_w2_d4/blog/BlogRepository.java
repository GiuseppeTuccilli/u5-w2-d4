package giuseppetuccilli.u5_w2_d4.blog;

import giuseppetuccilli.u5_w2_d4.autore.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByAutore(Autore autore);
}
