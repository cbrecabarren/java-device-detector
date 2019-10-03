package io.driocc.devicedetector.utils;

import com.google.common.primitives.Ints;

import java.util.Optional;

public class Utils {

	public static boolean isNumeric(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
	
	public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
	
	/**
	 * @param browserVersion
	 * @param version
	 * @return
	 */
	public static int versionCompare(String leftVersion, String rightVersion) {
		if(Utils.isEmpty(leftVersion))return -1;
		String[] vals1 = leftVersion.split("\\.");
	    String[] vals2 = rightVersion.split("\\.");
	    int i = 0;
	    // set index to first non-equal ordinal or length of shortest version string
	    while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
	      i++;
	    }
	    // compare first non-equal ordinal number
		if (i < vals1.length && i < vals2.length) {
			Integer value1 = Optional.ofNullable(Ints.tryParse(vals1[i])).orElse(0);
			Integer value2 = Optional.ofNullable(Ints.tryParse(vals2[i])).orElse(0);
			int diff = value1.compareTo(value2);
			return Integer.signum(diff);
	    }
	    // the strings are equal or one string is a substring of the other
	    // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
	    return Integer.signum(vals1.length - vals2.length);
	}
	
	public static int countChar(String s, char c) {
		int counter = 0;
		for( int i=0; i<s.length(); i++ ) {
		    if( s.charAt(i) == c ) {
		        counter++;
		    }
		}
		return counter;
	}
	
}
