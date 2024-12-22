package com.alura.literalura.model;

import jakarta.persistence.*;

/**
 * Clase que representa un libro en la base de datos.
 * Esta clase se mapea a la tabla "libros" y contiene información sobre los libros, incluyendo su autor asociado.
 */
@Entity
@Table(name = "libros")
public class Libro {

    /**
     * Identificador único del libro.
     * Este campo se genera automáticamente utilizando la estrategia IDENTITY.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título del libro.
     */
    private String titulo;

    /**
     * Idioma en el que está escrito el libro.
     */
    private String idioma;

    /**
     * Número de descargas del libro.
     */
    private int numeroDescargas;

    /**
     * Autor del libro.
     * Esta relación es bidireccional y se mapea a la entidad Autor mediante el campo "autor_id".
     */
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    // Getters y Setters

    /**
     * Obtiene el identificador único del libro.
     *
     * @return El identificador único del libro.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del libro.
     *
     * @param id El nuevo identificador único del libro.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return El título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del libro.
     *
     * @param titulo El nuevo título del libro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el idioma en el que está escrito el libro.
     *
     * @return El idioma del libro.
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Establece el idioma en el que está escrito el libro.
     *
     * @param idioma El nuevo idioma del libro.
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * Obtiene el número de descargas del libro.
     *
     * @return El número de descargas del libro.
     */
    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    /**
     * Establece el número de descargas del libro.
     *
     * @param numeroDescargas El nuevo número de descargas del libro.
     */
    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return El autor del libro.
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     *
     * @param autor El nuevo autor del libro.
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * Retorna una representación en cadena de texto del libro.
     *
     * @return Una cadena de texto que describe el libro, incluyendo su título, autor, idioma y número de descargas.
     */
    @Override
    public String toString() {
        return "LIBRO -----\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + (autor != null ? autor.getNombre() : "Desconocido") + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de descargas: " + numeroDescargas + "\n" +
                "--------------------";
    }
}