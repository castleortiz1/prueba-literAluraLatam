package com.alura.literalura.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Clase que representa un autor en la base de datos.
 * Esta clase se mapea a la tabla "autores" y contiene información sobre los autores, incluyendo sus libros asociados.
 */
@Entity
@Table(name = "autores")
public class Autor {

    /**
     * Identificador único del autor.
     * Este campo se genera automáticamente utilizando la estrategia IDENTITY.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del autor.
     */
    private String nombre;

    /**
     * Año de nacimiento del autor.
     */
    private int anoNacimiento;

    /**
     * Año de fallecimiento del autor.
     * Este campo es opcional y puede ser nulo si el autor aún está vivo.
     */
    private Integer anoFallecimiento;

    /**
     * Lista de libros escritos por el autor.
     * Esta relación es bidireccional y se mapea a la entidad Libro mediante el campo "autor".
     * La cascada ALL indica que todas las operaciones (persist, merge, remove, etc.) se propagarán a los libros asociados.
     * orphanRemoval = true indica que si un libro se elimina de la lista, también se eliminará de la base de datos.
     */
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros;

    // Getters y Setters

    /**
     * Obtiene el identificador único del autor.
     *
     * @return El identificador único del autor.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del autor.
     *
     * @param id El nuevo identificador único del autor.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del autor.
     *
     * @return El nombre del autor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del autor.
     *
     * @param nombre El nuevo nombre del autor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el año de nacimiento del autor.
     *
     * @return El año de nacimiento del autor.
     */
    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    /**
     * Establece el año de nacimiento del autor.
     *
     * @param anoNacimiento El nuevo año de nacimiento del autor.
     */
    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    /**
     * Obtiene el año de fallecimiento del autor.
     *
     * @return El año de fallecimiento del autor, o null si el autor aún está vivo.
     */
    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }

    /**
     * Establece el año de fallecimiento del autor.
     *
     * @param anoFallecimiento El nuevo año de fallecimiento del autor, o null si el autor aún está vivo.
     */
    public void setAnoFallecimiento(Integer anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }

    /**
     * Obtiene la lista de libros escritos por el autor.
     *
     * @return La lista de libros escritos por el autor.
     */
    public List<Libro> getLibros() {
        return libros;
    }

    /**
     * Establece la lista de libros escritos por el autor.
     *
     * @param libros La nueva lista de libros escritos por el autor.
     */
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    /**
     * Retorna una representación en cadena de texto del autor.
     *
     * @return Una cadena de texto que describe el autor, incluyendo su nombre, años de nacimiento y fallecimiento, y la cantidad de libros registrados.
     */
    @Override
    public String toString() {
        return "Autor: " + nombre + "\n" +
                "Fecha de nacimiento: " + anoNacimiento + "\n" +
                "Fecha de fallecimiento: " + (anoFallecimiento != null ? anoFallecimiento : "Desconocido") + "\n" +
                "Libros: " + (libros != null ? libros.size() : "Sin libros registrados");
    }
}