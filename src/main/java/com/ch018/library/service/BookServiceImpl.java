package com.ch018.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;
import com.ch018.library.form.AdvancedSearch;
import com.ch018.library.util.IConstants;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private LocalizationService localizationService;
	
	@Transactional
	public void addBook(Book book) {
		if (book.getImage() == null)
			book.setImage("http://placehold.it/120x150");
		bookDAO.addBook(book);
	}

	@Transactional
	public void updateBook(Book book) {
		if (book.getImage() == null)
			book.setImage("http://placehold.it/120x150");
		bookDAO.updateBook(book);
	}


	@Transactional
	public List<Book> getAllBooks() {
		return bookDAO.getAllBooks();
	}
	
	
	@Override
	@Transactional
	public List<Book> getAllBooks(int currentPos, int pageSize, String sort) {
		List<Localization> l = new ArrayList<>();
		List<Book> books = bookDAO.getAllBooks(currentPos, pageSize, sort);
		for (Book book : books) {
			Genre genre = book.getGenre();
			l.addAll(genre.getLocalizations());
			for (Localization localization : l) {
				if (localization.getLanguage().equals(LocaleContextHolder.getLocale().getLanguage())) {
					genre.setName(localization.getLocalizedName());
					break;
				}
			}
			book.setGenre(genre);
		}
		return books;
	}
	
	@Override
	@Transactional
	public long countBooks() {
		return bookDAO.countBooks();
	}
	
	/**
	 * Return count of books by params
	 * 
	 * @param search - search param (may be null)
	 * @param advancedSearch - advanced search param (may be null)
	 * @param genre - get all books by genre
	 * @return count of books
	 */
	@Override
	@Transactional
	public long countBooks(String search, AdvancedSearch advancedSearch, Integer genre) {
		if (genre == null) {
			if (search != null) {
				return bookDAO.simpleSearchCount(search);
			} else if (advancedSearch != null) {
				return bookDAO.advancedSearchCount(advancedSearch);
			} else {
				return bookDAO.countBooks();
			}
		} else {
			if (search != null) {
				return bookDAO.countBooksByGenre(search, genre);
			} else if (advancedSearch != null) {
				return bookDAO.countBooksByGenreWithAdvSearch(advancedSearch, genre);
			} else {
				return bookDAO.countBooksByGenre(search, genre);
			}
		}
	}
	
	/**
	 * Return all books by params
	 * 
	 * @param search - search param (may be null)
	 * @param advancedSearch - advanced search param (may be null)
	 * @param genre - get all books by genre (may be null)
	 * @param position - position for paging (may be null)
	 * @return List of books
	 */
	@Override
	@Transactional
	public List<Book> getBooks(String search, AdvancedSearch advancedSearch,
			Integer genre, Integer position) {
		if (genre == null) {
			if (search != null) {
				return bookDAO.simpleSearch(search, position, IConstants.PAGE_SIZE, "id");
			} else if (advancedSearch != null) {
				return bookDAO.advancedSearch(advancedSearch, position, IConstants.PAGE_SIZE);
			} else {
				return bookDAO.getAllBooks(position, IConstants.PAGE_SIZE, "id");
			}
		} else {
			if (search != null) {
				return bookDAO.getBooksByGenre(search, genre, position, IConstants.PAGE_SIZE, "id");
			} else if (advancedSearch != null) {
				return bookDAO.getBooksByGenreWithAdvSearch(advancedSearch, genre, position, IConstants.PAGE_SIZE);
			} else {
				return bookDAO.getBooksByGenre("", genre, position, IConstants.PAGE_SIZE, "id");
			}
		}
	}

	@Transactional
	public Book getBooksById(int id) {
		return bookDAO.getBooksById(id);
	}
	
	@Transactional
	public Book getBooksByIdWithUses(int id) {
		return bookDAO.getBooksByIdWithUses(id);
	}
	
	@Transactional
	public Book getBooksByIdWithOrders(int id) {
		return bookDAO.getBooksByIdWithOrders(id);
	}

	@Transactional
	public int deleteBook(int id) {
		return bookDAO.deleteBook(id);
	}

	@Transactional
	public List<Book> simpleSearch(String parametr) {
		return bookDAO.simpleSearch(parametr);
	}
	
	@Transactional
	public List<Book> simpleSearch(String parametr, int currentPos, int pageSize, String sort) {
		List<Book> books = bookDAO.simpleSearch(parametr, currentPos, pageSize, sort);
		List<Localization> l = new ArrayList<>();
		for (Book book : books) {
			Genre genre = book.getGenre();
			l.addAll(genre.getLocalizations());
			for (Localization localization : l) {
				if (localization.getLanguage().equals(LocaleContextHolder.getLocale().getLanguage())) {
					genre.setName(localization.getLocalizedName());
					break;
				}
			}
			book.setGenre(genre);
		}
		return books;
	}
	
	@Transactional
	public List<Book> paramSearch(String searchField, String search,
			int currentPos, int pageSize, String sort) {
		List<Book> books = bookDAO.paramSearch(searchField, search, currentPos, pageSize, sort);
		List<Localization> l = new ArrayList<>();
		for (Book book : books) {
			Genre genre = book.getGenre();
			l.addAll(genre.getLocalizations());
			for (Localization localization : l) {
				if (localization.getLanguage().equals(LocaleContextHolder.getLocale().getLanguage())) {
					genre.setName(localization.getLocalizedName());
				}
			}
			book.setGenre(genre);
		}
		return books;
	}

	

    @Override
    @Transactional
    public long simpleSearchCount(String search) {
    	return bookDAO.simpleSearchCount(search);
    }
    
    @Override
    @Transactional
    public long countBooksByGenre(String search, Integer id) {
    	return bookDAO.countBooksByGenre(search, id);
    }
    
    @Override
    @Transactional
    public List<Book> getBooksByGenre(String search, Integer id, int currentPos, int pageSize,	String field) {
    	return bookDAO.getBooksByGenre(search, id, currentPos, pageSize, field);
    }
    
    @Override
    @Transactional
    public List<Book> advancedSearch(AdvancedSearch search, int currentPos,	int pageSize) {
    	return bookDAO.advancedSearch(search, currentPos, pageSize);
    }
    
    @Override
    @Transactional
    public long advancedSearchCount(AdvancedSearch search) {
    	return bookDAO.advancedSearchCount(search);
    }

	@Override
	@Transactional
	public List<Book> getBooksByRating() {
		return bookDAO.getBooksByRating();
	}
	
	@Override
	@Transactional
	public long countBooksByGenreWithAdvSearch(AdvancedSearch advancedSearch, Integer id) {
		return bookDAO.countBooksByGenreWithAdvSearch(advancedSearch, id);
	}
	
	@Override
	@Transactional
	public List<Book> getBooksByGenreWithAdvSearch(AdvancedSearch advancedSearch, Integer id, int currentPos, int pageSize) {
		return bookDAO.getBooksByGenreWithAdvSearch(advancedSearch, id, currentPos, pageSize);
	}

}
