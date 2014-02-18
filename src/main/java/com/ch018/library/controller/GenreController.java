package com.ch018.library.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.LocalizationService;
import com.ch018.library.util.JsonResponse;
/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
@Controller
public class GenreController implements ViewPreparer {
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private LocalizationService localizationService;

	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) {
		Locale locale = LocaleContextHolder.getLocale();
		List<Genre> genres = genreService.getAllGenres(locale.getLanguage());
		attributeContext.putAttribute("genres", new Attribute(genres));
	}
	
	@RequestMapping(value = "/genres", method = RequestMethod.GET)
	public String showGenres(Model model) {
		List<Genre> genres = new ArrayList<>(genreService.getAllGenres(LocaleContextHolder.getLocale().getLanguage()));
		model.addAttribute("genres", genres);
		return "genres";
	}
	
	@RequestMapping(value = "/genre/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse newGenre(@RequestParam String enname, @RequestParam String runame, @RequestParam String uaname) {
		JsonResponse resp = new JsonResponse();
		Set<Localization> localizations = new HashSet<>();
		Genre genre = new Genre();
		genreService.addGenre(genre);
		Localization localizationEn = new Localization();
		localizationEn.setLanguage("en");
		localizationEn.setLocalizedName(enname);
		localizations.add(localizationEn);
		localizationEn.setGenre(genre);
		localizationService.addGenreLocalization(localizationEn);
		
		Localization localizationRu = new Localization();
		localizationRu.setLanguage("ru");
		localizationRu.setLocalizedName(runame);
		localizations.add(localizationRu);
		localizationRu.setGenre(genre);
		localizationService.addGenreLocalization(localizationRu);
		
		Localization localizationUa = new Localization();
		localizationUa.setLanguage("ua");
		localizationUa.setLocalizedName(uaname);
		localizations.add(localizationUa);
		localizationUa.setGenre(genre);
		localizationService.addGenreLocalization(localizationUa);
		
		/*if (!result.hasErrors()) {
			resp.setStatus("SUCCESS");
			if (book.getId() == 0) {
				log.info("New book {}", book);
				saveImage(book, file, session);
				bookService.addBook(book);
			} else {
				log.info("Update book {}", book);
				saveImage(book, file, session);
				bookService.updateBook(book);
			}
			resp.setResult(book);
		} else {
			Map<String,String> errors = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				String[] resolveMessageCodes = result.resolveMessageCodes(fieldError.getCode());
				String string = resolveMessageCodes[0];
				String message = messageSource.getMessage(string + "." + fieldError.getField(), new Object[]{fieldError.getRejectedValue()},LocaleContextHolder.getLocale());
				errors.put(fieldError.getField(), message);
				log.error("Error updating book {}", message);
			}
			resp.setErrorsMap(errors);	
			resp.setStatus("FAIL");
			resp.setResult(result.getAllErrors());
		}
*/		return resp;
	}
	
	

}
