package in.dibasbehera.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.dibasbehera.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	Slice<Book> findAllByTitle (String title, Pageable pageable);
	
	Page<Book> findAllByPagesGreaterThan(Integer pages, Pageable pageable);

	Slice<Book> findByTitleLike (String title, Pageable pageable);
	
	Slice<Book> findById(Integer id, Pageable pageable);

}
