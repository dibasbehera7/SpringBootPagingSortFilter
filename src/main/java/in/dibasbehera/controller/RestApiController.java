package in.dibasbehera.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import in.dibasbehera.entities.Book;
import in.dibasbehera.service.BookService;

@RestController
public class RestApiController {

	@Autowired
	BookService bookService;
		
	@RequestMapping("/booklists")	
	public ModelAndView index() {
		
		List<Book> books = bookService.getAllBooks();
		
		Map<String, Object> params = new HashMap<>();
		params.put("books", books);
		
		return new ModelAndView("showBookList",params);
	}
	
}
