package io.driocc.devicedetector;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class PatternCaching {

	private static final LoadingCache<String, Pattern> PATTERN_CACHE = CacheBuilder.newBuilder()
		.maximumSize(5000)
		.build(
			new CacheLoader<String, Pattern>() {
				public Pattern load(String regex) {
					return Pattern.compile(regex);
				}
			}
		);

	private static final LoadingCache<String, Pattern> PATTERN_INSENSITIVE_CACHE = CacheBuilder.newBuilder()
			.maximumSize(5000)
			.build(
				new CacheLoader<String, Pattern>() {
					public Pattern load(String regex) {
						return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					}
				}
			);

	public static Pattern getPattern(String regex) {
		Pattern pattern = null;
		try {
			pattern = PATTERN_CACHE.get(regex);
		} catch (ExecutionException e) {
		}
		return pattern;
	}

	public static Pattern getPatternInsensitive(String regex) {
		Pattern pattern = null;
		try {
			pattern = PATTERN_INSENSITIVE_CACHE.get(regex);
		} catch (ExecutionException e) {
		}
		return pattern;
	}
}
