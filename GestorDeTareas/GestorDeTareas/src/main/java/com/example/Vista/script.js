// URL del backend
const API_URL = "http://localhost:4560/tareas";

// Obtener elementos del DOM
const nuevaTareaInput = document.getElementById('nueva-tarea');
const estadoTareaSelect = document.getElementById('estado-tarea');
const agregarBtn = document.getElementById('agregar-btn');
const listaTareas = document.getElementById('lista-tareas');

let tareaEditadaIndex = null;  // Guardaremos el índice de la tarea a editar

// Función para mostrar tareas desde el backend
function mostrarTareas() {
    fetch(API_URL)
        .then(response => response.json())
        .then(tareas => {
            listaTareas.innerHTML = '';
            tareas.forEach((tarea, index) => {
                const li = document.createElement('li');
                const estadoClase = tarea.estado.replace(/\s+/g, '_');
                li.classList.add(tarea.estado);
                li.innerHTML = `
                    <span>${tarea.nombre}</span>
                    <div>
                        <button class="editar" onclick="editarTarea(${index})"><i class="fas fa-edit"></i> Editar</button>
                        <button class="eliminar" onclick="eliminarTarea(${index})"><i class="fas fa-trash"></i> Eliminar</button>
                    </div>
                `;
                listaTareas.appendChild(li);
            });
        });
}

// Función para agregar o actualizar tarea
agregarBtn.addEventListener('click', () => {
    const nombreTarea = nuevaTareaInput.value.trim();
    const estadoTarea = estadoTareaSelect.value;
    const tarea = { nombre: nombreTarea, estado: estadoTarea };

    if (nombreTarea === '') {
        alert('Por favor, ingresa una tarea.');
        return;
    }

    if (tareaEditadaIndex !== null) {
        // Actualizar tarea existente
        fetch(`${API_URL}/${tareaEditadaIndex}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(tarea)
        }).then(() => mostrarTareas());
        tareaEditadaIndex = null;
        agregarBtn.textContent = 'Agregar'; // Volver al modo agregar
    } else {
        // Agregar nueva tarea
        fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(tarea)
        }).then(() => mostrarTareas());
    }

    nuevaTareaInput.value = '';
    estadoTareaSelect.value = 'pendiente';
});

// Función para eliminar tarea
function eliminarTarea(index) {
    fetch(`${API_URL}/${index}`, {
        method: 'DELETE'
    }).then(() => mostrarTareas());
}

// Función para editar tarea
function editarTarea(index) {
    fetch(API_URL)
        .then(response => response.json())
        .then(tareas => {
            nuevaTareaInput.value = tareas[index].nombre;
            estadoTareaSelect.value = tareas[index].estado;
            tareaEditadaIndex = index;
            agregarBtn.textContent = 'Actualizar'; // Cambiar el botón a "Actualizar"
        });
}

// Mostrar tareas al cargar la página
mostrarTareas();

