# Proyecto_PPM: Wishify 

Este proyecto es una aplicación de **Wishlists** desarrollada en **Android Studio**. Su propósito principal es permitir a los usuarios crear, organizar y gestionar listas de deseos personalizadas.  

La version final esta en la rama mvvm.
Link de video del funcionamiento: 

## Características Principales  
- **Gestión de listas:**  
  - Crear, editar y eliminar listas de deseos.  
  - Asignar diferentes categorías y tipos a cada lista.  

- **Navegación Intuitiva:**  
  Utiliza un sistema de navegación basado en `NavHost` para acceder a diferentes pantallas, como:  

- **Consumo de API REST:**  
  - **Retrofit** para interactuar con un backend y manejar datos de categorías y productos.  
  - Clases como `WishAPI` y `WishWebService` para realizar las peticiones necesarias.
    
- **Consumo de Firebase:**
  - Para el registro de usuarios.
  - Para el registro de listas y sus productos.

## Tecnologías Utilizadas  
- **Lenguaje:** Kotlin  
- **Patrones:** MVVM (Model-View-ViewModel)  
- **Herramientas:**  
  - Firebase (Base de datos)  
  - Retrofit (Consumo de APIs)  
  - Jetpack Compose (Interfaz de usuario moderna y declarativa)  


 
