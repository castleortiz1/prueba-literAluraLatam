package com.alura.literalura.service;

import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar libros.
 * Esta clase proporciona métodos para realizar operaciones CRUD y consultas específicas sobre libros.
 */
@Service
public class LibroService {

    /**
     * Repositorio de libros.
     * Inyectado automáticamente por Spring.
     */
    @Autowired
    private LibroRepository libroRepository;

    /**
     * Lista todos los libros registrados en la base de datos.
     *
     * @return Una lista de todos los libros.
     */
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    /**
     * Lista libros por idioma.
     *
     * @param idioma El idioma de los libros a buscar.
     * @return Una lista de libros que coinciden con el idioma especificado.
     */
    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    /**
     * Crea un nuevo libro en la base de datos.
     *
     * @param libro El objeto Libro a crear.
     * @return El libro creado.
     */
    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    /**
     * Obtiene un libro por su identificador único.
     *
     * @param id El identificador único del libro.
     * @return Un Optional que contiene el libro si se encuentra, o Optional.empty() si no se encuentra.
     */
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    /**
     * Obtiene un libro por su título, ignorando mayúsculas y minúsculas.
     *
     * @param titulo El título del libro a buscar.
     * @return Un Optional que contiene el libro si se encuentra, o Optional.empty() si no se encuentra.
     */
    public Optional<Libro> obtenerLibroPorTitulo(String titulo) {
        return libroRepository.findByTituloIgnoreCase(titulo);
    }

    /**
     * Actualiza los detalles de un libro existente.
     *
     * @param id El identificador único del libro a actualizar.
     * @param libroDetalles El objeto Libro con los detalles actualizados.
     * @return El libro actualizado.
     * @throws RuntimeException Si el libro no se encuentra en la base de datos.
     */
    public Libro actualizarLibro(Long id, Libro libroDetalles) {
        Libro libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        libro.setTitulo(libroDetalles.getTitulo());
        libro.setIdioma(libroDetalles.getIdioma());
        libro.setNumeroDescargas(libroDetalles.getNumeroDescargas());
        libro.setAutor(libroDetalles.getAutor());
        return libroRepository.save(libro);
    }

    /**
     * Elimina un libro por su identificador único.
     *
     * @param id El identificador único del libro a eliminar.
     */
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}