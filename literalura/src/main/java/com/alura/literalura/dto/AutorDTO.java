package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase DTO (Data Transfer Object) para representar un autor.
 * Esta clase se utiliza para transferir datos relacionados con un autor entre diferentes capas de la aplicación.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutorDTO {

    /**
     * Nombre del autor.
     * Esta propiedad se mapea al campo "name" en el JSON.
     */
    @JsonProperty("name")
    private String nombre;

    /**
     * Año de nacimiento del autor.
     * Esta propiedad se mapea al campo "birth_year" en el JSON.
     */
    @JsonProperty("birth_year")
    private int anoNacimiento;

    /**
     * Año de fallecimiento del autor.
     * Esta propiedad se mapea al campo "death_year" en el JSON.
     */
    @JsonProperty("death_year")
    private int anoFallecimiento;

    // Getters y Setters

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
     * @return El año de fallecimiento del autor.
     */
    public int getAnoFallecimiento() {
        return anoFallecimiento;
    }

    /**
     * Establece el año de fallecimiento del autor.
     *
     * @param anoFallecimiento El nuevo año de fallecimiento del autor.
     */
    public void setAnoFallecimiento(int anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }
}