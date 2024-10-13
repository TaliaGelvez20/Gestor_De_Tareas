package com.example.controller;

import com.example.config.CORSFilter;
import com.example.model.Tarea;
import com.google.gson.Gson;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;
/**
 * Controlador para gestionar las operaciones CRUD sobre las tareas utilizando Spark como framework de microservicios.
 *
 * Este controlador expone varias rutas para permitir la creación, modificación, listado y eliminación
 * de tareas mediante el uso de HTTP y un backend en Java con Spark. La comunicación se realiza en formato JSON.
 */

public class TareasController {
    // Lista estática que almacena las tareas en memoria
    private static List<Tarea> tareas = new ArrayList<>();
    // Objeto Gson para convertir entre JSON y objetos Java
    private static Gson gson = new Gson();

    //Método principal que inicia el servidor llamando a startServer()
    public static void main(String[] args) {
        startServer(); // Llamar al método que inicia el servidor
    }

    /**
     * Configura y arranca el servidor en el puerto 4560.
     * También se aplican los filtros CORS para permitir peticiones entre dominios.
     */
    public static void startServer() {
        // Configura el puerto en el que se ejecutará el servidor
        Spark.port(4560);
        // Aplica el filtro CORS para permitir peticiones desde otros dominios
        CORSFilter cors = new CORSFilter();
        cors.applyCORS();

        /**
         * Ruta GET para obtener todas las tareas.
         * Devuelve una lista de todas las tareas en formato JSON.
         */
        Spark.get("/tareas", (req, res) -> {
            res.type("application/json");
            return gson.toJson(tareas);
        });

        /**
         * Ruta POST para agregar una nueva tarea.
         * Recibe un objeto tarea en el cuerpo de la solicitud en formato JSON.
         * Agrega la tarea a la lista y devuelve la tarea añadida en formato JSON.
         */
        Spark.post("/tareas", (req, res) -> {
            res.type("application/json");
            Tarea nuevaTarea = gson.fromJson(req.body(), Tarea.class);
            tareas.add(nuevaTarea);
            return gson.toJson(nuevaTarea);
        });

        /**
         * Ruta PUT para editar una tarea existente.
         * Recibe el índice de la tarea a modificar como parámetro y la nueva tarea en el cuerpo de la solicitud.
         * Actualiza la tarea en la lista y devuelve la tarea modificada en formato JSON.
         * Si el índice no es válido, devuelve un error 404.
         */
        Spark.put("/tareas/:index", (req, res) -> {
            res.type("application/json");
            int index = Integer.parseInt(req.params(":index"));
            Tarea tareaEditada = gson.fromJson(req.body(), Tarea.class);
            if (index >= 0 && index < tareas.size()) {
                tareas.set(index, tareaEditada);
                return gson.toJson(tareaEditada);
            } else {
                res.status(404);
                return "Tarea no encontrada";
            }
        });
        /**
         * Ruta DELETE para eliminar una tarea existente.
         * Recibe el índice de la tarea a eliminar como parámetro.
         * Elimina la tarea de la lista y devuelve la tarea eliminada en formato JSON.
         * Si el índice no es válido, devuelve un error 404.
         */
        Spark.delete("/tareas/:index", (req, res) -> {
            res.type("application/json");// Especifica que la respuesta será JSON
            int index = Integer.parseInt(req.params(":index")); // Obtiene el índice de la tarea a eliminar
            // Verifica si el índice es válido
            if (index >= 0 && index < tareas.size()) {
                Tarea tareaEliminada = tareas.remove(index); // Elimina la tarea del índice especificado
                return gson.toJson(tareaEliminada); // Devuelve la tarea eliminada
            } else {
                res.status(404); // Establece el código de error 404 si el índice no es válido
                return "Tarea no encontrada"; // Devuelve un mensaje de error
            }
        });
    }
}
