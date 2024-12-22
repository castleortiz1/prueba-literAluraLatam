//LoggingConfig
package com.alura.literalura.config;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de logging personalizada.
 * Esta clase ajusta los niveles de logging para componentes específicos como HikariCP y Hibernate.
 */
@Configuration
public class LoggingConfig {

    /**
     * Constructor de la clase LoggingConfig.
     * Se ejecuta al inicializar la configuración de logging.
     */
    public LoggingConfig() {
        // Obtener el contexto de logger de Logback
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        // Ajustar niveles de registro para HikariCP
        Logger hikariLogger = loggerContext.getLogger("com.zaxxer.hikari");
        hikariLogger.setLevel(Level.INFO);
        // Establece el nivel de logging para HikariCP en INFO para reducir la verbosidad
        // INFO: muestra mensajes de nivel INFO y superiores (WARN, ERROR)

        // Ajustar niveles de registro para Hibernate
        Logger hibernateSqlLogger = loggerContext.getLogger("org.hibernate.SQL");
        hibernateSqlLogger.setLevel(Level.INFO);
        // Establece el nivel de logging para las consultas SQL de Hibernate en INFO
        // INFO: muestra las consultas SQL ejecutadas por Hibernate

        Logger hibernateBinderLogger = loggerContext.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        hibernateBinderLogger.setLevel(Level.WARN);
        // Establece el nivel de logging para los valores bindeados en las consultas SQL en WARN
        // WARN: muestra solo mensajes de advertencia y superiores

        Logger hibernateHbm2ddlLogger = loggerContext.getLogger("org.hibernate.tool.hbm2ddl");
        hibernateHbm2ddlLogger.setLevel(Level.INFO);
        // Establece el nivel de logging para la generación de esquemas de Hibernate en INFO
        // INFO: muestra mensajes de nivel INFO y superiores

        Logger hibernateTransactionLogger = loggerContext.getLogger("org.hibernate.engine.transaction.internal.TransactionImpl");
        hibernateTransactionLogger.setLevel(Level.INFO);
        // Establece el nivel de logging para las transacciones de Hibernate en INFO
        // INFO: muestra mensajes de nivel INFO y superiores

        Logger hibernateConnectionLogger = loggerContext.getLogger("org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl");
        hibernateConnectionLogger.setLevel(Level.INFO);
        // Establece el nivel de logging para el proveedor de conexiones JDBC de Hibernate en INFO
        // INFO: muestra mensajes de nivel INFO y superiores
    }
}