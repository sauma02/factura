/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */



const API_BASE_URL = "http://localhost:1011/api/productos";
let dataTableInstance = null;

// Función principal para obtener productos
async function getProductos() {
    try {
        showLoader();
        
        const response = await fetch(`${API_BASE_URL}/lista`, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`Error ${response.status}: ${errorText}`);
        }

        const data = await response.json();
        renderProductos(data);
    } catch (error) {
        showError(error.message);
    } finally {
        hideLoader();
    }
}

// Renderizar productos
function renderProductos(productos) {
    const tbody = document.getElementById('tbody-productos');
    
    tbody.innerHTML = productos.map(producto => `
        <tr data-id="${producto.id}">
            <td>${producto.id}</td>
            <td>${producto.codigoBarras || 'N/A'}</td>
            <td>${producto.nombre}</td>
            <td>${producto.descripcion.substring(0, 50)}${producto.descripcion.length > 50 ? '...' : ''}</td>
            <td>${formatCurrency(producto.precio)}</td>
            <td>${producto.stock}</td>
            <td>${producto.categoria || 'Sin categoría'}</td>
            <td>
                <button class="btn btn-sm btn-warning btn-edit">
                    <i class="fas fa-edit"></i>
                </button>
                <button class="btn btn-sm btn-danger btn-delete">
                    <i class="fas fa-trash"></i>
                </button>
            </td>
        </tr>
    `).join('');

    refreshDataTable();
}

// Actualizar DataTable
function refreshDataTable() {
    if (dataTableInstance) {
        dataTableInstance.destroy();
    }
    
    dataTableInstance = $('#tabla-productos').DataTable({
        responsive: true,
        
    });
}


function showLoader() {
    document.getElementById('tbody-productos').innerHTML = `
        <tr>
            <td colspan="8" class="text-center">
                <div class="spinner-border text-primary" role="status">
                    <span class="visually-hidden">Cargando...</span>
                </div>
            </td>
        </tr>`;
}

// Helpers

function formatCurrency(value) {
    try {
        // Validación y conversión segura
        const numericValue = Number(value) || 0;
        
        return new Intl.NumberFormat('es-CO', { // Locale correcto
            style: 'currency',
            currency: 'COP',
            minimumFractionDigits: 0, // COP no usa decimales
            maximumFractionDigits: 0,
            useGrouping: true
        }).format(numericValue);
    } catch (error) {
        console.error("Error formateando:", value, error);
        // Fallback manual
        return `$${numericValue.toLocaleString('es')} COP`; 
    }
}
function hideLoader() {
    // Limpiar solo si no hay datos
    if (document.getElementById('tbody-productos').children.length === 1) {
        document.getElementById('tbody-productos').innerHTML = '';
    }
}

function showError(message) {
    document.getElementById('tbody-productos').innerHTML = `
        <tr>
            <td colspan="8" class="text-center text-danger">
                <i class="fas fa-exclamation-triangle me-2"></i>${message}
            </td>
        </tr>`;
}



// Inicialización
document.addEventListener('DOMContentLoaded', () => {
    // Carga inicial
    getProductos();
    
    // Configurar botón de actualización manual
    document.getElementById('refresh-button').addEventListener('click', () => {
        getProductos();
    });
});