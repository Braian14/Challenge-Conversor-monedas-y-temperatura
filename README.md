Casa de Cambio Alura - Conversor de Monedas y Temperaturas
Este proyecto es una aplicación Java que permite realizar conversiones entre diferentes monedas y temperaturas, utilizando una interfaz gráfica 
amigable basada en JOptionPane. La funcionalidad de conversión de monedas está respaldada por la API ExchangeRate-API, mientras que las 
conversiones de temperatura se realizan localmente mediante cálculos matemáticos.

Funcionalidades Principales
Conversor de Monedas

Conversión entre diversas monedas utilizando tasas de cambio actualizadas desde ExchangeRate-API.
Monedas soportadas:
Dólares (USD) a Soles (PEN) y viceversa.
Dólares (USD) a Pesos Argentinos (ARS) y viceversa.
Dólares (USD) a Pesos Colombianos (COP) y viceversa.
Dólares (USD) a Real Brasileño (BRL) y viceversa.
Conversor de Temperaturas

Soporta conversiones entre las siguientes unidades:
Celsius ↔ Kelvin
Celsius ↔ Fahrenheit
Kelvin ↔ Fahrenheit
Interfaz Gráfica

Menús desplegables para facilitar la selección de las opciones.

Cómo Funciona
Al iniciar el programa, aparece un menú principal con las opciones:

Conversor de Monedas

Conversor de Temperaturas

Salir

Dependiendo de la selección:

Conversor de Monedas: Se muestra un menú desplegable con las opciones de monedas soportadas. Después de seleccionar una opción, el usuario ingresa el valor a convertir, y el resultado aparece en una ventana emergente.
Conversor de Temperaturas: Similar al de monedas, pero con conversiones de temperatura.
El usuario puede repetir las operaciones tantas veces como desee, o elegir "Salir" para cerrar el programa.

Ejemplo de Uso
Selecciona "Conversor de Monedas".
Escoge "Dólares → Soles".
Ingresa el valor a convertir (por ejemplo: 100).
El programa mostrará algo como:

El valor 100.00 (USD) tiene una tasa de cambio de 360.50 (PEN)
