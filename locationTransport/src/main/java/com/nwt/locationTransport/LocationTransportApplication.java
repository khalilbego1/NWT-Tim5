package com.nwt.locationTransport;

import com.nwt.locationTransport.Entities.*;
import com.nwt.locationTransport.Repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class LocationTransportApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationTransportApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertData(CityRepo cityRepo, CountryRepo countryRepo, DestinationRepo destinationRepo, RegionRepo regionRepo, TransportationRepo transportationRepo, TransportationTypeRepo transportationTypeRepo) {
		return (args) -> {
			Country c1 =new Country("CountryTest");
			countryRepo.save(c1);
			Region r1= new Region("RegionTest",c1);
			regionRepo.save(r1);
			City ct1= new City("CityTest",r1);
			cityRepo.save(ct1);
			Destination d1 = new Destination("DestinationTest",ct1);
			destinationRepo.save(d1);
			TransportationType tt1= new TransportationType("Test");
			transportationTypeRepo.save(tt1);
			Transportation t1 = new Transportation("TransportationTest",tt1);
			transportationRepo.save(t1);
		};
	}


}
