package org.vaadin.example;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.UI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Route("")  // Esta es la ruta principal, se verá al iniciar la aplicación
@PageTitle("Login")
public class LoginView extends VerticalLayout {

    public LoginView() {
        setSizeFull();  // Configura el layout para ocupar todo el espacio disponible
        setAlignItems(Alignment.CENTER);  // Centra el contenido horizontalmente
        setJustifyContentMode(JustifyContentMode.CENTER);  // Centra el contenido verticalmente


        // Configuración del formulario de inicio de sesión
        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");

        Button loginButton = new Button("Login", event -> {
            String username = usernameField.getValue();
            String password = passwordField.getValue();

            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "http://localhost:8081/auth/login";  // Endpoint de autenticación

            try {
                AuthRequest authRequest = new AuthRequest(username, password);
                ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, authRequest, String.class);


                if (response.getStatusCode().is2xxSuccessful()) {
                    String userRole = response.getBody();  // Asume que la respuesta contiene el rol del usuario

                    if ("admin".equalsIgnoreCase(userRole)) {
                        UI.getCurrent().navigate("AdminView");  // Redirigir a la vista de administrador
                    } else if ("user".equalsIgnoreCase(userRole)) {
                        UI.getCurrent().navigate("UserView");  // Redirigir a la vista de usuario
                    } else {
                        Notification.show("Unknown role");
                    }
                } else {
                    Notification.show("Invalid credentials");
                }
            } catch (Exception ex) {
                Notification.show("An error occurred during login");
            }
        });

        add(usernameField, passwordField, loginButton);  // Añadir componentes al layout
    }

    public static class AuthRequest {
        private String username;
        private String password;

        public AuthRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}


