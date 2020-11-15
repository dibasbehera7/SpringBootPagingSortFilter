package in.dibasbehera.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.dibasbehera.entities.Book;
import in.dibasbehera.repository.BookRepository;
import in.dibasbehera.service.BookService;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> getAllBooks() {
		List<Book> list = bookRepository.findAll(); 
		return list;
	}

	
}
