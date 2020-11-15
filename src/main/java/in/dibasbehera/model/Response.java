package in.dibasbehera.model;

import java.util.List;

import in.dibasbehera.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	private List<Book> books;
	private int totalPages;
	private int pageNumber;
	private int pageSize;
	
}
