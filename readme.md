-- FLUJO COMPLETO OAUTH2 CON GOOGLE -- 
Cuando un usuario pulsa "Iniciar sesión con Google" en la app móvil, esto es lo que ocurre:

La app móvil abre un navegador con la URL de Google
El usuario acepta los permisos en Google
Google redirige de vuelta a tu backend con un código de autorización
Tu backend intercambia ese código por un token de acceso
Tu backend usa ese token para obtener el perfil del usuario de Google
Tu backend busca si ese usuario ya existe en tu BD o lo crea nuevo
Tu backend genera un JWT propio y se lo devuelve a la app móvil
La app móvil usa ese JWT en todas las peticiones futuras.

Cliente
    │
    ▼
/oauth2/authorization/google
    │
    ▼
Google Login
    │
    ▼
Google devuelve el usuario autenticado
    │
    ▼
Spring Security recibe el usuario
    │
    ▼
Aplicación genera un JWT
    │
    ▼
Cliente guarda el JWT
    │
    ▼
Peticiones posteriores:
Authorization: Bearer <tu_jwt>