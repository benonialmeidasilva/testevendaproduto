package com.testeubs.teste.com.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractReader {
	
    public static String readJson(String path) throws IOException {
    	return String.join(" ", Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
    }
    
}
