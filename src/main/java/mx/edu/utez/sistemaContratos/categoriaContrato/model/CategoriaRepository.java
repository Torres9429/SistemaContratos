package mx.edu.utez.sistemaContratos.categoriaContrato.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByStatusIsTrue();
}
