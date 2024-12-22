package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Autor.
 * Esta interfaz proporciona métodos para interactuar con la tabla "autores" en la base de datos.
 */
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    /**
     * Busca un autor por su nombre.
     *
     * @param nombre El nombre del autor a buscar.
     * @return Un Optional que contiene el autor si se encuentra, o Optional.empty() si no se encuentra.
     */
    Optional<Autor> findByNombre(String nombre);

    /**
     * Busca autores que estuvieran vivos en un año específico y sus libros asociados.
     *
     * @param ano El año en el que se desea buscar autores vivos.
     * @return Una lista de autores vivos en el año especificado con sus libros asociados.
     */
    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros WHERE (a.anoFallecimiento IS NULL OR a.anoFallecimiento > :ano) AND a.anoNacimiento <= :ano")
    List<Autor> findAutoresVivosEnAnoConLibros(@Param("ano") int ano);

    /**
     * Busca todos los autores junto con sus libros asociados.
     *
     * @return Una lista de todos los autores con sus libros asociados.
     */
    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllConLibros();
}