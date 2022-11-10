package ee.secretagency.endofthegame;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EndOfTheGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndOfTheGameApplication.class, args);
    }


    //use @Bean for direct creation of spring beans
   /* @Bean
    public Calculator calculator() {
        return new Calculator();
    }
*/

}
