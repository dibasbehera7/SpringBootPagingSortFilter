package in.dibasbehera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.dibasbehera.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
