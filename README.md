Presentado Por: 
Talia Yaritza Gelvez Gelvez (Ingeniería de Software)
Paula Andrea Ramirez Casilimas (Ingeniería de Software)


Gestor de Tareas

Este proyecto es una aplicación sencilla para la gestión de tareas, implementada con un backend en Java y un frontend en HTML, CSS y JavaScript. Permite a los usuarios agregar, editar y eliminar tareas, las cuales se gestionan en un servidor que expone una API REST. La arquitectura utilizada es Modelo-Vista-Controlador (MVC), que facilita la separación de responsabilidades y mejora la escalabilidad y el mantenimiento del sistema.
Descripción

La aplicación tiene como objetivo proporcionar una plataforma simple pero efectiva para la gestión de tareas, donde los usuarios pueden crear, editar y eliminar tareas, asignarles estados (pendiente, en proceso, finalizada) y visualizarlas de manera organizada. Los cambios realizados en las tareas se reflejan automáticamente en la interfaz gracias a la interacción con el backend a través de una API REST.
Características del Gestor de Tareas:

    Agregar Tareas: Los usuarios pueden añadir nuevas tareas con un estado inicial (pendiente, en proceso o finalizada).
    Editar Tareas: Se permite modificar tanto el nombre como el estado de las tareas.
    Eliminar Tareas: Las tareas pueden ser eliminadas del sistema cuando ya no son necesarias.
    Estados de Tareas: Las tareas se visualizan con diferentes estilos dependiendo de su estado.

Estructura del Proyecto

    Frontend: Desarrollado en HTML, CSS y JavaScript. El archivo index.html proporciona la estructura de la página, mientras que el archivo style.css aplica los estilos y script.js maneja la lógica de interacción con la API REST del backend.
    Backend: Implementado en Java, el backend gestiona las operaciones CRUD a través de una API REST que se comunica con el frontend mediante solicitudes HTTP.

Instalación y Ejecución
Requisitos Previos

    Java: Se debe tener una versión reciente de Java instalada para ejecutar el backend.
    Postman: Se recomienda para probar y verificar las peticiones HTTP.
    Navegador Web: Cualquier navegador moderno soportará la ejecución del frontend.

Pasos para la Ejecución

    Clonar el repositorio:

    bash

git clone <URL_DEL_REPOSITORIO>
cd gestor-de-tareas

Ejecutar el Backend: Utilizando IntelliJ IDEA u otro entorno de desarrollo, ejecuta el servidor Java. Asegúrate de que el servidor esté configurado para escuchar en localhost:4560:

bash

java -jar servidor.jar

Probar la API con Postman: Puedes utilizar Postman para verificar las solicitudes y respuestas del servidor:

    GET /tareas: Obtiene todas las tareas.
    POST /tareas: Crea una nueva tarea.
    PUT /tareas/{id}: Actualiza una tarea existente.
    DELETE /tareas/{id}: Elimina una tarea.

Ejemplo de solicitud POST:

json

    {
      "nombre": "Estudiar para el examen",
      "estado": "pendiente"
    }

    Abrir el Frontend: Abre el archivo index.html en un navegador. La interfaz del gestor de tareas cargará automáticamente las tareas desde el backend y permitirá agregar, editar o eliminar tareas.

Uso de la Aplicación

    Agregar una tarea: El usuario puede ingresar el nombre de una tarea en el campo de texto, seleccionar un estado, y presionar el botón "Agregar".
    Editar una tarea: Se puede editar una tarea existente haciendo clic en el botón de edición, lo que permitirá cambiar tanto el nombre como el estado.
    Eliminar una tarea: Al presionar el botón "Eliminar", la tarea seleccionada se borrará del sistema.

Arquitectura del Software

Este proyecto sigue la arquitectura Modelo-Vista-Controlador (MVC), que separa la lógica de presentación, la lógica de negocio y la gestión de datos. El frontend en HTML y JavaScript sirve como la Vista, el backend en Java actúa como el Controlador, y los datos de las tareas se manejan como el Modelo. Esta separación facilita el mantenimiento, permite futuras expansiones y asegura que el código sea modular y escalable.
Herramientas Utilizadas

    Java: Lenguaje de programación utilizado para el backend.
    HTML/CSS: Utilizados para estructurar y dar estilo a la interfaz de usuario.
    JavaScript: Se utiliza para manejar la interacción con la API REST y la dinámica de la aplicación.
    Postman: Herramienta utilizada para realizar pruebas de las solicitudes HTTP del backend.
