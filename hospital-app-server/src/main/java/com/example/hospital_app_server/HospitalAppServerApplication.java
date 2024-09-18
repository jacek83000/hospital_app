package com.example.hospital_app_server;

import com.example.hospital_app_server.entity.Doctor;
import com.example.hospital_app_server.entity.Medication;
import com.example.hospital_app_server.entity.Receipt;
import com.example.hospital_app_server.repository.DoctorRepository;
import com.example.hospital_app_server.repository.ReceiptRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@SpringBootApplication
public class HospitalAppServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalAppServerApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return runner -> {
			System.out.println("CommandLineRunner------------------------------------------");
		};
	}
}
