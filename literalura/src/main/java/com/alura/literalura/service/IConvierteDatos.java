package com.alura.literalura.service;

/**
 * Interfaz para convertir datos JSON a objetos DTO.
 * Esta interfaz define un método genérico para deserializar JSON en objetos Java.
 */
public interface IConvierteDatos {

    /**
     * Convierte una cadena de texto JSON en un objeto de la clase especificada.
     *
     * @param json  La cadena de texto JSON que se va a deserializar.
     * @param clase La clase a la que se va a convertir el JSON.
     * @param <T>   El tipo de objeto que se va a retornar.
     * @return Un objeto de la clase especificada que contiene los datos deserializados del JSON.
     */
    <T> T obtenerDatos(String json, Class<T> clase);
}