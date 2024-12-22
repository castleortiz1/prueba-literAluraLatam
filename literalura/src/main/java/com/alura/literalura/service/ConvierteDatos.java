package com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * Servicio para convertir datos JSON a objetos DTO.
 * Esta clase implementa la interfaz IConvierteDatos y proporciona métodos para deserializar JSON en objetos Java.
 */
@Component
public class ConvierteDatos implements IConvierteDatos {

    /**
     * Instancia de ObjectMapper para deserializar JSON.
     * ObjectMapper es una clase de la biblioteca Jackson que se utiliza para convertir JSON en objetos Java.
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Convierte una cadena de texto JSON en un objeto de la clase especificada.
     *
     * @param json  La cadena de texto JSON que se va a deserializar.
     * @param clase La clase a la que se va a convertir el JSON.
     * @param <T>   El tipo de objeto que se va a retornar.
     * @return Un objeto de la clase especificada que contiene los datos deserializados del JSON.
     * @throws RuntimeException Si ocurre un error durante la deserialización del JSON.
     */
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            // Deserializa el JSON en un objeto de la clase especificada
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            // Lanza una RuntimeException si ocurre un error de procesamiento de JSON
            throw new RuntimeException("Error al deserializar el JSON", e);
        }
    }
}