package com.ch018.library.service;

import org.springframework.stereotype.Component;

@Component
public interface LocalizationService {
	String getName(int id, String language);
}
