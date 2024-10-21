# Apply Digital Challenge - Android Compose Application

## Descripción

Este proyecto es una aplicación Android desarrollada con **Jetpack Compose** que conecta con la API de **Hacker News** para mostrar artículos recientes sobre Android e iOS. La aplicación permite a los usuarios refrescar la lista de artículos, visualizar los posts en una vista web interna y eliminar elementos individualmente mediante un swipe.

## Requerimientos Implementados

1. **Conexión a la API de Hacker News**: En el arranque y cuando se realiza una acción de pull-to-refresh, la app se conecta a la API de Hacker News:  
   `https://hn.algolia.com/api/v1/search_by_date?query=mobile`,  
   para obtener artículos recientes relacionados con Android o iOS.
2. **Modo Offline**: Si la app se utiliza sin conexión, se muestran los elementos descargados previamente desde la API.
3. **Lista de artículos**: La vista principal muestra una lista de artículos. Al hacer tap en un artículo, se abre una `WebView` interna para visualizar el contenido enlazado.
4. **Eliminar artículos**: Los usuarios pueden deslizar (swipe) cualquier artículo para eliminarlo de la vista. Los artículos eliminados no reaparecen al refrescar los datos, manteniendo su estado incluso cuando la lista se actualiza.
5. **No optimizado para tabletas**: No se ha implementado una interfaz optimizada para tabletas, siguiendo los requerimientos.

## Estructura del Proyecto

- Clean Architecture
La aplicación está organizada siguiendo los principios de Clean Architecture, lo que permite una clara separación de responsabilidades y facilita la escalabilidad y el mantenimiento del código.

- Data Layer: Contiene la implementación del repositorio y las fuentes de datos (local y remota).
- RemoteDataSource: Obtiene los datos desde la API de Hacker News.
- LocalDataSource: Gestiona el almacenamiento local utilizando Room para guardar los datos y manejar los artículos eliminados.
- Domain Layer: Define los casos de uso de la aplicación, que son interactuados desde la capa de presentación. También incluye la interfaz del repositorio.
- Presentation Layer: Contiene toda la lógica relacionada con la interfaz de usuario y los ViewModels. Se comunica con la capa de dominio para obtener los datos necesarios y actualizar la UI.

### `HomeScreenViewModel`
El `HomeScreenViewModel` gestiona la lógica central de la aplicación, incluyendo:

El `HomeScreenViewModel` maneja la lógica de la pantalla principal, encargándose de cargar las
noticias desde el `HackerNewsRepository` y de gestionar eventos de usuario como el pull-to-refresh y
la eliminación de artículos. Inicializa el estado de la UI mediante `loadNews()` y utiliza
corrutinas con `viewModelScope` para ejecutar tareas en segundo plano. Los eventos como el
deslizamiento para eliminar y el pull-to-refresh son gestionados en el método `onEvent()`, que
actualiza la lista de noticias y el estado de carga de la UI.

### Casos de Uso
Por simplicidad no se usaron Uses Cases, pero reemplazarian al Repositorio en el ViewModel.


### `HomeScreen`
La pantalla principal muestra una lista de artículos recientes, permitiendo a los usuarios actualizar la lista y eliminar artículos.

#### Componentes Clave:
- **Pull-to-Refresh**: El usuario puede actualizar la lista de artículos tirando hacia abajo la vista de la lista.
- **ItemCard**: Cada artículo se representa con una `Card` que permite eliminar artículos mediante un gesto de swipe y abrir el enlace en una vista web.

### `WebViewScreen`
Esta pantalla muestra el artículo seleccionado dentro de una `WebView` integrada en la aplicación. Proporciona una barra superior para navegación y acceso rápido a la pantalla anterior.

#### Detalles Técnicos:
- **WebView**: Permite cargar la página web enlazada en el artículo seleccionado dentro de la app, evitando abrir navegadores externos.
- **Scaffold**: Contiene la estructura de la UI con una barra superior para la navegación.

## Dependencias

### Jetpack Compose
- **Compose UI, Material 3**: Usado para construir la interfaz de usuario declarativa utilizando Material Design 3.
- **Compose Tooling Preview**: Facilita la previsualización de componentes de UI en tiempo real en Android Studio.

### Room (Base de Datos Local)
- **Room Runtime**: Utilizado para almacenar los artículos descargados de la API y mantener los artículos eliminados.
- **Room KTX**: Proporciona extensiones para usar corrutinas con Room.

### Retrofit y OkHttp (Networking)
- **Retrofit**: Librería utilizada para realizar peticiones HTTP a la API REST de Hacker News.
- **Gson Converter**: Convierte las respuestas JSON de la API en objetos Kotlin.
- **OkHttp Logging Interceptor**: Permite registrar las peticiones y respuestas HTTP para depuración.

### Koin (Inyección de Dependencias)
- **Koin Core, Android, Compose**: Usado para la inyección de dependencias en la aplicación.

### Splash Screen API
- **androidx.core.splashscreen**: Utilizada para mostrar una pantalla de carga inicial cuando se arranca la aplicación.

## Decisiones de Implementación

1. **Uso de Retrofit para Networking**: Retrofit fue seleccionado por su facilidad de uso y su capacidad para manejar la conversión de JSON a objetos Kotlin.
2. **Persistencia con Room**: Room fue utilizado para almacenar los artículos descargados y gestionar los artículos eliminados, permitiendo una experiencia offline fluida.
3. **Jetpack Compose para UI**: Se optó por Compose para construir una interfaz de usuario moderna y declarativa, permitiendo una rápida iteración en el diseño.
4. **Pull-to-Refresh**: La funcionalidad de actualización se implementó utilizando el patrón de suspensiones y corrutinas para manejar la lógica de actualización de manera asíncrona.

## Suposiciones Realizadas
- Se asume que los datos de la API siempre son válidos y están en el formato esperado.
- Los artículos eliminados se almacenan localmente y no se envía ninguna información de eliminación a la API.
- No se maneja el estado de error cuando una Url de un Articulo sea incorrecta.

