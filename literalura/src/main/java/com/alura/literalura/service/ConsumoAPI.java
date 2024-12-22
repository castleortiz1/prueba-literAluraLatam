package com.alura.literalura.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Servicio para consumir datos de una API externa.
 * Esta clase proporciona métodos para realizar solicitudes HTTP y obtener datos en formato JSON.
 */
@Component
public class ConsumoAPI {

    /**
     * Obtiene datos de una URL específica utilizando una solicitud HTTP GET.
     *
     * @param url La URL de la API desde la cual se obtendrán los datos.
     * @return Una cadena de texto que contiene los datos en formato JSON obtenidos de la API.
     * @throws RuntimeException Si ocurre un error de E/S o si la solicitud es interrumpida.
     */
    public String obtenerDatos(String url) {
        // Crea una instancia de HttpClient para realizar solicitudes HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Construye una solicitud HTTP GET con la URL proporcionada
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Envía la solicitud HTTP y obtiene la respuesta
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            // Lanza una RuntimeException si ocurre un error de E/S
            throw new RuntimeException("Error de E/S al obtener datos de la API", e);
        } catch (InterruptedException e) {
            // Lanza una RuntimeException si la solicitud es interrumpida
            throw new RuntimeException("La solicitud fue interrumpida", e);
        }

        // Obtiene el cuerpo de la respuesta en formato de cadena de texto
        String json = response.body();
        return json;
    }
}