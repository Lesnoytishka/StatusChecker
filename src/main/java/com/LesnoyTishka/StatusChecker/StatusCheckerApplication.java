package com.LesnoyTishka.StatusChecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StatusCheckerApplication {
// Из минусов: при запуске сервера обновление работоспособности не происходит.
// Первое обновление происходит при провм посещении сайта.
// Далее у всех будет корректное отображение данных

	public static void main(String[] args) {
		SpringApplication.run(StatusCheckerApplication.class, args);
	}

}
