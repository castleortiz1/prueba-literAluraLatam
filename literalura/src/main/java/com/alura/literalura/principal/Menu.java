package com.alura.literalura.principal;

import com.alura.literalura.dto.AutorDTO;
import com.alura.literalura.dto.LibroDTO;
import com.alura.literalura.dto.RespuestaLibrosDTO;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.service.AutorService;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Clase principal que maneja el menú de interacción con el usuario.
 * Esta clase proporciona opciones para buscar libros, listar libros registrados, listar autores registrados,
 * listar autores vivos en un año específico y listar libros por idioma.
 */
@Component
public class Menu {

    /**
     * Servicio para gestionar libros.
     * Inyectado automáticamente por Spring.
     */
    @Autowired
    private LibroService libroService;

    /**
     * Servicio para gestionar autores.
     * Inyectado automáticamente por Spring.
     */
    @Autowired
    private AutorService autorService;

    /**
     * Servicio para consumir datos de una API externa.
     * Inyectado automáticamente por Spring.
     */
    @Autowired
    private ConsumoAPI consumoAPI;

    /**
     * Servicio para convertir datos JSON a objetos DTO.
     * Inyectado automáticamente por Spring.
     */
    @Autowired
    private ConvierteDatos convierteDatos;

    /**
     * URL base de la API externa de libros.
     */
    private static final String BASE_URL = "https://gutendex.com/books/";

    /**
     * Muestra el menú principal y maneja las opciones seleccionadas por el usuario.
     */
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("--- LITERALURA ---");
            System.out.println("1 - Buscar libro por título");
            System.out.println("2 - Listar libros registrados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos en un año");
            System.out.println("5 - Listar libros por idioma");
            System.out.println("0 - Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    try {
                        String encodedTitulo = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
                        String json = consumoAPI.obtenerDatos(BASE_URL + "?search=" + encodedTitulo);
                        RespuestaLibrosDTO respuestaLibrosDTO = convierteDatos.obtenerDatos(json, RespuestaLibrosDTO.class);
                        List<LibroDTO> librosDTO = respuestaLibrosDTO.getLibros();
                        if (librosDTO.isEmpty()) {
                            System.out.println("Libro no encontrado en la API");
                        } else {
                            boolean libroRegistrado = false;
                            for (LibroDTO libroDTO : librosDTO) {
                                if (libroDTO.getTitulo().equalsIgnoreCase(titulo)) {
                                    Optional<Libro> libroExistente = libroService.obtenerLibroPorTitulo(titulo);
                                    if (libroExistente.isPresent()) {
                                        System.out.println("Detalle: Clave (titulo)=(" + titulo + ") ya existe");
                                        System.out.println("No se puede registrar el mismo libro más de una vez");
                                        libroRegistrado = true;
                                        break;
                                    } else {
                                        Libro libro = new Libro();
                                        libro.setTitulo(libroDTO.getTitulo());
                                        libro.setIdioma(libroDTO.getIdiomas().get(0));
                                        libro.setNumeroDescargas(libroDTO.getNumeroDescargas());

                                        // Buscar o crear el Autor
                                        AutorDTO primerAutorDTO = libroDTO.getAutores().get(0);
                                        Autor autor = autorService.obtenerAutorPorNombre(primerAutorDTO.getNombre())
                                                .orElseGet(() -> {
                                                    Autor nuevoAutor = new Autor();
                                                    nuevoAutor.setNombre(primerAutorDTO.getNombre());
                                                    nuevoAutor.setAnoNacimiento(primerAutorDTO.getAnoNacimiento());
                                                    nuevoAutor.setAnoFallecimiento(primerAutorDTO.getAnoFallecimiento());
                                                    return autorService.crearAutor(nuevoAutor);
                                                });

                                        // Asociar el Autor al Libro
                                        libro.setAutor(autor);

                                        // Guardar el libro en la base de datos
                                        libroService.crearLibro(libro);
                                        System.out.println("Libro registrado: " + libro.getTitulo());
                                        mostrarDetallesLibro(libroDTO);
                                        libroRegistrado = true;
                                        break;
                                    }
                                }
                            }
                            if (!libroRegistrado) {
                                System.out.println("No se encontró un libro exactamente con el título '" + titulo + "' en la API");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error al obtener datos de la API: " + e.getMessage());
                    }
                    break;
                case 2:
                    libroService.listarLibros().forEach(libro -> {
                        System.out.println("------LIBRO--------");
                        System.out.println("Título: " + libro.getTitulo());
                        System.out.println("Autor: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "Desconocido"));
                        System.out.println("Idioma: " + libro.getIdioma());
                        System.out.println("Número de descargas: " + libro.getNumeroDescargas());
                    });
                    break;
                case 3:
                    autorService.listarAutores().forEach(autor -> {
                        System.out.println("-------AUTOR-------");
                        System.out.println("Autor: " + autor.getNombre());
                        System.out.println("Fecha de nacimiento: " + autor.getAnoNacimiento());
                        System.out.println("Fecha de fallecimiento: " + (autor.getAnoFallecimiento() != null ? autor.getAnoFallecimiento() : "Desconocido"));
                        String libros = autor.getLibros().stream()
                                .map(Libro::getTitulo)
                                .collect(Collectors.joining(", "));
                        System.out.println("Libros: [ " + libros + " ]");
                    });
                    break;
                case 4:
                    System.out.print("Ingrese el año vivo de autor(es) que desea buscar: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    List<Autor> autoresVivos = autorService.listarAutoresVivosEnAno(ano);
                    if (autoresVivos.isEmpty()) {
                        System.out.println("No se encontraron autores vivos en el año " + ano);
                    } else {
                        autoresVivos.forEach(autor -> {
                            System.out.println("-------AUTOR-------");
                            System.out.println("Autor: " + autor.getNombre());
                            System.out.println("Fecha de nacimiento: " + autor.getAnoNacimiento());
                            System.out.println("Fecha de fallecimiento: " + (autor.getAnoFallecimiento() != null ? autor.getAnoFallecimiento() : "Desconocido"));
                            System.out.println("Libros: " + autor.getLibros().size());
                        });
                    }
                    break;
                case 5:
                    System.out.println("Ingrese el idioma:");
                    System.out.println("es");
                    System.out.println("en");
                    System.out.println("fr");
                    System.out.println("pt");
                    String idioma = scanner.nextLine();
                    if ("es".equalsIgnoreCase(idioma) || "en".equalsIgnoreCase(idioma) || "fr".equalsIgnoreCase(idioma) || "pt".equalsIgnoreCase(idioma)) {
                        libroService.listarLibrosPorIdioma(idioma).forEach(libro -> {
                            System.out.println("------LIBRO--------");
                            System.out.println("Título: " + libro.getTitulo());
                            System.out.println("Autor: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "Desconocido"));
                            System.out.println("Idioma: " + libro.getIdioma());
                            System.out.println("Número de descargas: " + libro.getNumeroDescargas());
                        });
                    } else {
                        System.out.println("Idioma no válido. Intente de nuevo.");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    /**
     * Muestra los detalles de un libro DTO.
     *
     * @param libroDTO El objeto LibroDTO cuyos detalles se van a mostrar.
     */
    private void mostrarDetallesLibro(LibroDTO libroDTO) {
        System.out.println("------LIBRO--------");
        System.out.println("Título: " + libroDTO.getTitulo());
        System.out.println("Autor: " + (libroDTO.getAutores().isEmpty() ? "Desconocido" : libroDTO.getAutores().get(0).getNombre()));
        System.out.println("Idioma: " + libroDTO.getIdiomas().get(0));
        System.out.println("Número de descargas: " + libroDTO.getNumeroDescargas());
    }
}