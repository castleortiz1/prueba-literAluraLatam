package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Clase DTO (Data Transfer Object) para representar la respuesta de una solicitud de libros.
 * Esta clase se utiliza para transferir datos relacionados con una lista de libros desde una fuente externa (por ejemplo, una API) hacia la aplicación.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaLibrosDTO {

    /**
     * Lista de libros.
     * Esta propiedad se mapea al campo "results" en el JSON y contiene objetos de tipo LibroDTO.
     */
    @JsonProperty("results")
    private List<LibroDTO> libros;

    /**
     * Obtiene la lista de libros.
     *
     * @return La lista de libros.
     */
    public List<LibroDTO> getLibros() {
        return libros;
    }

    /**
     * Establece la lista de libros.
     *
     * @param libros La nueva lista de libros.
     */
    public void setLibros(List<LibroDTO> libros) {
        this.libros = libros;
    }
}