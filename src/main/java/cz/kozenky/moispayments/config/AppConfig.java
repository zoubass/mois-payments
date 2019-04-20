package cz.kozenky.moispayments.config;

import cz.kozenky.moispayments.converter.PaymentDtoIntoPaymentMapper;
import cz.kozenky.moispayments.model.codelist.CategoryList;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public MapperFactory mapperFactory(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build(); 
        mapperFactory.getConverterFactory().registerConverter(new PaymentDtoIntoPaymentMapper());
        return mapperFactory;
    }
    
    @Bean
    public CategoryList categories(){
        return new CategoryList();
    }
}
