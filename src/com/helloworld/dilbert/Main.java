/**
 * Wally
 * 
 * @author  Alec Hartline, alechartline.github.io and Phoebus Yang github.com/yang132
 * 
 * @version 17 September 2017
 * 
 */

package com.helloworld.dilbert;

import java.awt.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	private static final int COMMAND_COUNT = 25;

	public static void main(String[] args) throws IOException {
		
		if(args.length < 1)
			throw new IOException();
		
		System.out.println();
		
		String code = getFileInput(args[0]);
		
		lexData(code);
		
		System.out.println();
		
	}

	private static void lexData(String code) {
		String[] tokenStrings = code.split("\\s");
		
		String regex = "";
		for(int i = COMMAND_COUNT+1; i >= 1; --i){
			regex += String.format("(0{%d}|0%d)|", i, i);
		}
		regex = regex.substring(0, regex.length()-1);
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(code);
		
		ArrayList<String> tokens = new ArrayList<String>();
		
		while(matcher.find())
			tokens.add(matcher.group());
		
		int[] output = new int[tokens.size()];
		
		for(int i = 0; i < output.length; ++i) {
			String s = tokens.get(i);
			if(s.replaceAll("0", "").length() == 0)
				output[i] = s.length();
			else
				output[i] = Integer.parseInt(s.replaceFirst("0", ""));
			//System.out.print(output[i] + ", ");
		}
		
		Commands.runCommands(output);
		
	}

	private static String getFileInput(String path) {
		
		try {
			return new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
