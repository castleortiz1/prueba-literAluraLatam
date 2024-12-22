package com.alura.literalura;

import com.alura.literalura.principal.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Literalura.
 * Esta clase es el punto de entrada de la aplicación Spring Boot.
 */
@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	/**
	 * Inyección de dependencias del componente Menu.
	 * El componente Menu se inyecta automáticamente mediante la anotación @Autowired.
	 */
	@Autowired
	private final Menu menu;

	/**
	 * Constructor de la clase LiteraluraApplication.
	 * Se utiliza para inyectar la dependencia Menu.
	 *
	 * @param menu Instancia de la clase Menu inyectada por Spring.
	 */
	public LiteraluraApplication(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Punto de entrada principal de la aplicación Spring Boot.
	 * Este método se ejecuta cuando se inicia la aplicación.
	 *
	 * @param args Argumentos de línea de comandos pasados al iniciar la aplicación.
	 */
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
		// Por qué: Este método inicia la aplicación Spring Boot.
		// Cómo: Llama al método run de SpringApplication, pasando la clase LiteraluraApplication y los argumentos de línea de comandos.
		// Cuándo: Se ejecuta cuando se inicia el programa desde la línea de comandos o desde un IDE.
	}

	/**
	 * Método que se ejecuta después de que la aplicación Spring Boot ha iniciado.
	 * Implementa la interfaz CommandLineRunner para ejecutar tareas iniciales.
	 *
	 * @param args Argumentos de línea de comandos pasados al iniciar la aplicación.
	 * @throws Exception Si ocurre alguna excepción durante la ejecución.
	 */
	@Override
	public void run(String... args) throws Exception {
		menu.mostrarMenu();
		// Por qué: Este método se utiliza para mostrar el menú principal de la aplicación después de que Spring Boot ha iniciado.
		// Cómo: Llama al método mostrarMenu de la instancia inyectada de Menu.
		// Cuándo: Se ejecuta automáticamente después de que la aplicación Spring Boot ha completado su inicio.
	}
}