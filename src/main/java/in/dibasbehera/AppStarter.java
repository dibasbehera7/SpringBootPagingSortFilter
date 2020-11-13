package in.dibasbehera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("in.dibasbehera")
@EntityScan("in.dibasbehera")
@EnableJpaRepositories("in.dibasbehera")
public class AppStarter{

	/*
	 * @Autowired BookService bookService;
	 */
	
	public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
        
    }
	
	/*
	 * @Override public void run(String... args) throws Exception {
	 * bookService.getAllBooks().forEach(book -> {
	 * 
	 * System.out.println(book.getTitle());
	 * 
	 * }); }
	 */

}
