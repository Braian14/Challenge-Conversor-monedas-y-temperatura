import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.*;

public class CasadeCambioAluraApp {
    private static final String API_KEY = "769e520ca53664d60237154e";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        while (true) {
            // Menú principal
            String[] menuPrincipal = { "Conversor de Monedas", "Conversor de Temperaturas", "Salir" };
            String seleccionPrincipal = (String) JOptionPane.showInputDialog(
                    null,
                    "Bienvenido a tu Casa de Cambio Alura\n\nSeleccione una opción:",
                    "Menú Principal",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    menuPrincipal,
                    menuPrincipal[0]
            );

            if (seleccionPrincipal == null || seleccionPrincipal.equals("Salir")) {
                JOptionPane.showMessageDialog(null, "Gracias por su preferencia.");
                break;
            }

            try {
                if (seleccionPrincipal.equals("Conversor de Monedas")) {
                    mostrarMenuMonedas();
                } else if (seleccionPrincipal.equals("Conversor de Temperaturas")) {
                    mostrarMenuTemperaturas();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
            } catch (IOException | InterruptedException e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la conversión: " + e.getMessage());
            }
        }
    }

    // Método para mostrar el menú de monedas
    public static void mostrarMenuMonedas() throws IOException, InterruptedException {
        String[] opcionesMonedas = {
                "Dólares --> Soles",
                "Soles --> Dólares",
                "Dólares --> Pesos Argentinos",
                "Pesos Argentinos --> Dólares",
                "Dólares --> Pesos Colombianos",
                "Pesos Colombianos --> Dólares",
                "Dólares --> Real Brasileño",
                "Real Brasileño --> Dólares"
        };

        String seleccionMoneda = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una conversión de moneda:",
                "Conversor de Monedas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesMonedas,
                opcionesMonedas[0]
        );

        if (seleccionMoneda == null) return;

        String from = "";
        String to = "";

        switch (seleccionMoneda) {
            case "Dólares --> Soles" -> { from = "USD"; to = "PEN"; }
            case "Soles --> Dólares" -> { from = "PEN"; to = "USD"; }
            case "Dólares --> Pesos Argentinos" -> { from = "USD"; to = "ARS"; }
            case "Pesos Argentinos --> Dólares" -> { from = "ARS"; to = "USD"; }
            case "Dólares --> Pesos Colombianos" -> { from = "USD"; to = "COP"; }
            case "Pesos Colombianos --> Dólares" -> { from = "COP"; to = "USD"; }
            case "Dólares --> Real Brasileño" -> { from = "USD"; to = "BRL"; }
            case "Real Brasileño --> Dólares" -> { from = "BRL"; to = "USD"; }
        }

        String valorStr = JOptionPane.showInputDialog(
                null,
                "Ingrese el valor a convertir:",
                "Valor a Convertir",
                JOptionPane.QUESTION_MESSAGE
        );

        if (valorStr == null) return;

        double valor = Double.parseDouble(valorStr);
        convertirMoneda(from, to, valor);
    }

    // Método para mostrar el menú de temperaturas
    public static void mostrarMenuTemperaturas() {
        String[] opcionesTemp = {
                "Celsius a Kelvin",
                "Kelvin a Celsius",
                "Celsius a Fahrenheit",
                "Fahrenheit a Celsius",
                "Kelvin a Fahrenheit",
                "Fahrenheit a Kelvin"
        };

        String seleccionTemp = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una conversión de temperatura:",
                "Conversor de Temperaturas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesTemp,
                opcionesTemp[0]
        );

        if (seleccionTemp == null) return;

        String valorStr = JOptionPane.showInputDialog(
                null,
                "Ingrese el valor de la temperatura:",
                "Valor de Temperatura",
                JOptionPane.QUESTION_MESSAGE
        );

        if (valorStr == null) return;

        try {
            double valor = Double.parseDouble(valorStr);
            double resultado = 0;

            switch (seleccionTemp) {
                case "Celsius a Kelvin" -> resultado = valor + 273.15;
                case "Kelvin a Celsius" -> resultado = valor - 273.15;
                case "Celsius a Fahrenheit" -> resultado = (valor * 9 / 5) + 32;
                case "Fahrenheit a Celsius" -> resultado = (valor - 32) * 5 / 9;
                case "Kelvin a Fahrenheit" -> resultado = (valor - 273.15) * 9 / 5 + 32;
                case "Fahrenheit a Kelvin" -> resultado = (valor - 32) * 5 / 9 + 273.15;
            }

            JOptionPane.showMessageDialog(
                    null,
                    String.format("El resultado de la conversión es: %.2f", resultado),
                    "Resultado de la Conversión de Temperatura",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
        }
    }

    // Método para convertir monedas
    public static void convertirMoneda(String from, String to, double valor) throws IOException, InterruptedException {
        String url = API_URL + from;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        MonedaCambio monedaUtil = new MonedaCambio();
        double tasa = monedaUtil.obtenerTasaConversion(response.body(), to);

        double resultado = valor * tasa;

        JOptionPane.showMessageDialog(
                null,
                String.format("El valor %.2f (%s) tiene una tasa de cambio de %.2f (%s)", valor, from, resultado, to),
                "Resultado de la Conversión",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}