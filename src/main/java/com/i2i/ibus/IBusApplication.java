package com.i2i.ibus;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 28 2022
 *
 */
@SpringBootApplication
public class IBusApplication {

    @Bean
    public static ModelMapper mapper() {
	return new ModelMapper();
    }
    
    public static void main(String[] args) {
	SpringApplication.run(IBusApplication.class, args);
    }

}
