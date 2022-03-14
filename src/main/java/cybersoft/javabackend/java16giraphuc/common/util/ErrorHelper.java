package cybersoft.javabackend.java16giraphuc.common.util;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class ErrorHelper {
	public final static Pattern UUID_REGEX_PATTERN = Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");
	public static boolean checkId(String id) {
		if (id==null) {
			return false;
		}
		return UUID_REGEX_PATTERN.matcher(id).matches();
	}
	public static List<String> getAllError(BindingResult result){
		return result.getAllErrors()
				.stream()
				.map(t ->t.getDefaultMessage())
				.collect(Collectors.toList());
	}
}
