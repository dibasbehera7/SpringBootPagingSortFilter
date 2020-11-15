package in.dibasbehera.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import in.dibasbehera.entities.Book;
import in.dibasbehera.model.Response;
import in.dibasbehera.repository.BookRepository;
import in.dibasbehera.service.BookService;

@RestController
@RequestMapping("/api")
public class RestApiController {

	@Autowired
	BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@RequestMapping("/books")
	public ModelAndView index() {

		List<Book> books = bookService.getAllBooks();

		Map<String, Object> params = new HashMap<>();
		params.put("books", books);

		return new ModelAndView("showBookList", params);
	}

	// RESTAPIS FOR REQUESTING CUSTOMER LIST WITH PAGINATION

	// returns a Page object
	@GetMapping("/pageable")
	public Page<Book> retrieveBookWithPaging(@Param(value = "page") int page, @Param(value = "size") int size) {
		Pageable requestedPage = PageRequest.of(page, size);
		Page<Book> books = bookRepository.findAll(requestedPage);
		return books;
	}

	@GetMapping("/pageable/list")
	public List<Book> retrieveBookListWithPaging(@Param(value = "page") int page, @Param(value = "size") int size) {
		Pageable requestedPage = PageRequest.of(page, size);
		Page<Book> books = bookRepository.findAll(requestedPage);
		return books.toList();
	}

	@GetMapping("/custom/pageable")
	public Response retrieveBook(@Param(value = "page") int page, @Param(value = "size") int size) {
		Pageable requestedPage = PageRequest.of(page, size);
		Page<Book> books = bookRepository.findAll(requestedPage);
		Response res = new Response(books.getContent(), books.getTotalPages(), books.getNumber(), books.getSize());

		return res;
	}

	// RESTAPIS FOR CUSTOMER PAGINATION WITH PAGING AND FILTERING

	// filter by Title search
	@GetMapping("/filterByTitle")
	public Slice<Book> retrieveBookByTitleLIkeWithPaging(@Param(value = "title") String title,
			@Param(value = "page") int page, @Param(value = "size") int size) {
		Pageable requestedPage = PageRequest.of(page, size);
		Slice<Book> books = bookRepository.findByTitleLike("%"+title+"%", requestedPage);
		return books;
	}

	// is used to retrieve a Customer page with a title criteria for filtering
	@GetMapping("/pageablebytitle")
	public Slice<Book> retrieveBookByTitleWithPaging(@Param(value = "title") String title,
			@Param(value = "page") int page, @Param(value = "size") int size) {
		Pageable requestedPage = PageRequest.of(page, size);
		Slice<Book> books = bookRepository.findAllByTitle(title, requestedPage);
		return books;
	}
	
	//Get details by value
	@GetMapping("/pageablebyId")
	public Slice<Book> retrieveBookById(@Param(value = "id") String id, @Param(value = "page") int page,
			@Param(value = "size") int size) {
		Pageable requestedPage = PageRequest.of(page, size);
		Slice<Book> books = bookRepository.findById(Integer.valueOf(id), requestedPage);
		return books;
	}

	// is used to retrieve a Customer page with a pages criteria for filtering
	@GetMapping("/pageable/bypagegreaterthan")
	public Slice<Book> retrieveBookByPageGreaterThan(@Param(value = "pages") Integer pages,
			@Param(value = "page") int page, @Param(value = "size") int size) {
		Pageable requestedPage = PageRequest.of(page, size);
		Page<Book> books = bookRepository.findAllByPagesGreaterThan(pages, requestedPage);
		return books;
	}

	// RESTAPIS TO RETRIEVE CUSTOMER LIST WITH PAGINATION AND SORTING

	@GetMapping("/pagingandsorting")
	public Page<Book> pagingAndSortingCustomers(@Param(value = "page") int page, @Param(value = "size") int size) {
		Pageable requestedPage = PageRequest.of(page, size,
				Sort.by("title").descending().and(Sort.by("pages")).and(Sort.by("id")));
		Page<Book> books = bookRepository.findAll(requestedPage);
		return books;
	}

	// SPRINGBOOT RESTAPIS TO PAGINATION FILTERING AND SORTING
	@GetMapping("/pagingfilteringandsorting")
	public Page<Book> pagingFilteringAndSortingCustomersByAge(@Param(value = "title") String title,
			@Param(value = "pages") Integer pages, @Param(value = "page") int page, @Param(value = "size") int size) {
		Pageable requestedPage = PageRequest.of(page, size,
				Sort.by("title").descending().and(Sort.by("pages")).and(Sort.by("id")));

		Page<Book> books = bookRepository.findAllByPagesGreaterThan(pages, requestedPage);
		return books;
	}

}
