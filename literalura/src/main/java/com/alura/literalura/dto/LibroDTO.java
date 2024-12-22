package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Clase DTO (Data Transfer Object) para representar un libro.
 * Esta clase se utiliza para transferir datos relacionados con un libro entre diferentes capas de la aplicación.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDTO {

    /**
     * Identificador único del libro.
     * Esta propiedad se mapea al campo "id" en el JSON.
     */
    @JsonProperty("id")
    private int id;

    /**
     * Título del libro.
     * Esta propiedad se mapea al campo "title" en el JSON.
     */
    @JsonProperty("title")
    private String titulo;

    /**
     * Lista de autores del libro.
     * Esta propiedad se mapea al campo "authors" en el JSON y contiene objetos de tipo AutorDTO.
     */
    @JsonProperty("authors")
    private List<AutorDTO> autores;

    /**
     * Lista de idiomas en los que está disponible el libro.
     * Esta propiedad se mapea al campo "languages" en el JSON.
     */
    @JsonProperty("languages")
    private List<String> idiomas;

    /**
     * Número de descargas del libro.
     * Esta propiedad se mapea al campo "download_count" en el JSON.
     */
    @JsonProperty("download_count")
    private int numeroDescargas;

    // Getters y Setters

    /**
     * Obtiene el identificador único del libro.
     *
     * @return El identificador único del libro.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del libro.
     *
     * @param id El nuevo identificador único del libro.
     */
    public void setId(int id) {
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
     * Obtiene la lista de autores del libro.
     *
     * @return La lista de autores del libro.
     */
    public List<AutorDTO> getAutores() {
        return autores;
    }

    /**
     * Establece la lista de autores del libro.
     *
     * @param autores La nueva lista de autores del libro.
     */
    public void setAutores(List<AutorDTO> autores) {
        this.autores = autores;
    }

    /**
     * Obtiene la lista de idiomas en los que está disponible el libro.
     *
     * @return La lista de idiomas del libro.
     */
    public List<String> getIdiomas() {
        return idiomas;
    }

    /**
     * Establece la lista de idiomas en los que está disponible el libro.
     *
     * @param idiomas La nueva lista de idiomas del libro.
     */
    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
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
}