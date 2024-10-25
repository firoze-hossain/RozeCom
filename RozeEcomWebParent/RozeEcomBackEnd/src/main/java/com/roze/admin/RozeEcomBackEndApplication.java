package com.roze.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EntityScan({"com.roze.common.entity"})
public class RozeEcomBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(RozeEcomBackEndApplication.class, args);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Contraseña a codificar
        String passwordToEncode = "esemio123";

        // Codificar la contraseña
        String encodedPassword = passwordEncoder.encode(passwordToEncode);

        // Imprimir la contraseña codificada
        System.out.println("Contraseña esemio123 codificada es: " + encodedPassword);
	}

}
