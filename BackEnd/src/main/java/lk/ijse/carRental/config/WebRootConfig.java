package lk.ijse.carRental.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@Import({JPAConfig.class})
@ComponentScan(basePackages = "lk.ijse.carRental.service")
public class WebRootConfig {
    //this Config class is assigned for pojo's which is processing
    //DAOs and Business of the application

   public WebRootConfig(){
       System.out.println("WebRootConfig : Instantiated");
   }

   @Bean
   public ModelMapper modelMapper(){
      return new ModelMapper();
   }

   @Bean(name = "multipartResolver")
   public CommonsMultipartResolver multipartResolver() {
      CommonsMultipartResolver resolver = new CommonsMultipartResolver();
      resolver.setMaxUploadSize(50 * 1024 * 1024);
      return resolver;
   }

}
