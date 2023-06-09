package md.master.app.core.configs;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
public class PayPalConfig {
    @Value("${paypal.client-id}")
    private String clientId;

    @Value("${paypal.client-secret}")
    private String secret;

//    @Value("${paypal.mode}")
//    private String mode;
    private PayPalEnvironment environment;

    @PostConstruct
    private void init(){
        this.environment = new PayPalEnvironment.Sandbox(clientId,secret);
    }

    @Bean
    public PayPalHttpClient payPalHttpClient(){
        return new PayPalHttpClient(environment);
    }
}
