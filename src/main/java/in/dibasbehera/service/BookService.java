package in.dibasbehera.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import in.dibasbehera.entities.Book;

public interface BookService {

	List<Book> getAllBooks();
	
}
