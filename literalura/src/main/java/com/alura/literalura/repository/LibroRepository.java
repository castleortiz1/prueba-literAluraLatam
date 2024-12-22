package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Libro.
 * Esta interfaz proporciona métodos para interactuar con la tabla "libros" en la base de datos.
 */
@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    /**
     * Busca un libro por su título, ignorando mayúsculas y minúsculas.
     *
     * @param titulo El título del libro a buscar.
     * @return Un Optional que contiene el libro si se encuentra, o Optional.empty() si no se encuentra.
     */
    Optional<Libro> findByTituloIgnoreCase(String titulo);

    /**
     * Busca libros por idioma.
     *
     * @param idioma El idioma de los libros a buscar.
     * @return Una lista de libros que coinciden con el idioma especificado.
     */
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> findByIdioma(@Param("idioma") String idioma);
}