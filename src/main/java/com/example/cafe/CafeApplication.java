package com.example.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class CafeApplication {

	public static void main(String[] args) {
		String password = "pass"; // Ваш пароль

		// Генерация хеша (автоматически включает соль)
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

		System.out.println("Hashed password: " + hashedPassword);

		// Проверка пароля
		boolean isCorrect = BCrypt.checkpw("pass", hashedPassword);
		System.out.println("Password correct? " + isCorrect);


		SpringApplication.run(CafeApplication.class, args);
	}

}
