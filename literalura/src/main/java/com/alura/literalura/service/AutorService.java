package com.alura.literalura.service;

import com.alura.literalura.model.Autor;
import com.alura.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar autores.
 * Esta clase proporciona métodos para realizar operaciones CRUD y consultas específicas sobre autores.
 */
@Service
public class AutorService {

    /**
     * Repositorio de autores.
     * Inyectado automáticamente por Spring.
     */
    @Autowired
    private AutorRepository autorRepository;

    /**
     * Lista todos los autores junto con sus libros asociados.
     *
     * @return Una lista de todos los autores con sus libros asociados.
     */
    public List<Autor> listarAutores() {
        return autorRepository.findAllConLibros();
    }

    /**
     * Lista autores que estuvieran vivos en un año específico.
     *
     * @param ano El año en el que se desea buscar autores vivos.
     * @return Una lista de autores vivos en el año especificado.
     */
    public List<Autor> listarAutoresVivosEnAno(int ano) {
        return autorRepository.findAutoresVivosEnAnoConLibros(ano);
    }

    /**
     * Crea un nuevo autor en la base de datos.
     *
     * @param autor El objeto Autor a crear.
     * @return El autor creado.
     */
    public Autor crearAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    /**
     * Obtiene un autor por su identificador único.
     *
     * @param id El identificador único del autor.
     * @return Un Optional que contiene el autor si se encuentra, o Optional.empty() si no se encuentra.
     */
    public Optional<Autor> obtenerAutorPorId(Long id) {
        return autorRepository.findById(id);
    }

    /**
     * Obtiene un autor por su nombre.
     *
     * @param nombre El nombre del autor a buscar.
     * @return Un Optional que contiene el autor si se encuentra, o Optional.empty() si no se encuentra.
     */
    public Optional<Autor> obtenerAutorPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre);
    }

    /**
     * Actualiza los detalles de un autor existente.
     *
     * @param id El identificador único del autor a actualizar.
     * @param autorDetalles El objeto Autor con los detalles actualizados.
     * @return El autor actualizado.
     * @throws RuntimeException Si el autor no se encuentra en la base de datos.
     */
    public Autor actualizarAutor(Long id, Autor autorDetalles) {
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        autor.setNombre(autorDetalles.getNombre());
        autor.setAnoNacimiento(autorDetalles.getAnoNacimiento());
        autor.setAnoFallecimiento(autorDetalles.getAnoFallecimiento());
        return autorRepository.save(autor);
    }

    /**
     * Elimina un autor por su identificador único.
     *
     * @param id El identificador único del autor a eliminar.
     */
    public void eliminarAutor(Long id) {
        autorRepository.deleteById(id);
    }
}