package Proj.library.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT) //строгое совпадение
                .setFieldMatchingEnabled(true) //разрешаем совпадение по полям
                .setSkipNullEnabled(true) //разрешаем пропускать поля, которые маппер не нашел и присваивать им null
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);//доступ к private-полям
        return modelMapper;
    }
}
