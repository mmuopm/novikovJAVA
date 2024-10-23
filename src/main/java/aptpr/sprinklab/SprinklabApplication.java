package aptpr.sprinklab;

import dbexamples.dao.BookDaoJDBC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SprinklabApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SprinklabApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Шаг 1
        BookDaoJDBC bookDaoJDBC = new BookDaoJDBC();
        bookDaoJDBC.findBookById(2);
        //
    }
}
