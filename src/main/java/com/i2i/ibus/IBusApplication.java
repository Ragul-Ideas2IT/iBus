package com.i2i.ibus;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Ragul
 *
 */
@SpringBootApplication
public class IBusApplication {

    @Bean
    public ModelMapper mapper() {
	return new ModelMapper();
    }
    public static void main(String[] args) {
	SpringApplication.run(IBusApplication.class, args);
    }

}
