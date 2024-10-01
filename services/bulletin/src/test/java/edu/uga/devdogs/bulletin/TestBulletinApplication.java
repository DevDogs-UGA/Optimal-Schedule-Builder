package edu.uga.devdogs.bulletin;

import org.springframework.boot.SpringApplication;

public class TestBulletinApplication {

	public static void main(String[] args) {
		SpringApplication.from(BulletinApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
