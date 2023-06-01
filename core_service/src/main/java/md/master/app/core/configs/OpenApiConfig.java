package md.master.app.core.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean
    public OpenAPI api(){
        return new OpenAPI().info(
                new Info()
                        .title("Market CORE Service.")
                        .version("1")
        );
    }
}
