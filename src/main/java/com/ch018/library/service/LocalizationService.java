package com.ch018.library.service;

import org.springframework.stereotype.Component;

import com.ch018.library.entity.Genre;

@Component
public interface LocalizationService {
	String getName(int id, String language);
}
