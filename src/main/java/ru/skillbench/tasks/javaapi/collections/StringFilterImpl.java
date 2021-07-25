package ru.skillbench.tasks.javaapi.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public class StringFilterImpl implements ru.skillbench.tasks.javaapi.collections.StringFilter {
    Set<String> set = new HashSet<>();

    @Override
    public void add(String s) {
        set.add(s == null ? null : s.toLowerCase());
    }

    @Override
    public boolean remove(String s) {
        return set.remove(s);
    }

    @Override
    public void removeAll() {
        set.clear();
    }

    @Override
    public Collection<String> getCollection() {
        return set;
    }

    private Iterator<String> getIteratorByPredicate(Predicate<String> predicate) {
        return set.stream().filter(predicate).iterator();
    }

    @Override
    public Iterator<String> getStringsContaining(String chars) {
        if (chars == null || chars.isEmpty()) {
            return set.iterator();
        }
        return getIteratorByPredicate(s -> s != null && s.contains(chars.toLowerCase()));
    }

    @Override
    public Iterator<String> getStringsStartingWith(String begin) {
        if (begin == null || begin.isEmpty()) {
            return set.iterator();
        }
        return getIteratorByPredicate(s -> s != null && s.startsWith(begin.toLowerCase()));
    }

    @Override
    public Iterator<String> getStringsByNumberFormat(String format) {
        if (format == null || format.isEmpty()) {
            return set.iterator();
        }

        return getIteratorByPredicate(s -> !checkStrByNumberFormat(format, s));
    }

    private boolean checkStrByNumberFormat(String format, String s) {
        if (s == null) {
            return false;
        }
        if (s.length() != format.length()) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (format.charAt(i) == '#' && !Character.isDigit(s.charAt(i))) {
                return true;
            }
            if (format.charAt(i) != '#' && format.charAt(i) != s.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<String> getStringsByPattern(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            return set.iterator();
        }
        return getIteratorByPredicate(s -> checkStrByPattern(s, pattern));
    }

    private boolean checkStrByPattern(String str, String pattern) {
        if (str == null) {
            return false;
        }
        if (pattern.endsWith("*")) {
            pattern += "$";
        }
        String[] split = pattern.split("\\*");
        if (split.length == 1) {
            return pattern.equals(str);
        }
        if (split.length == 2) {
            if (split[0].isEmpty()) { //*qwer
                return str.endsWith(split[1]);
            }
            if (split[1].equals("$")) { //qwer*
                return str.startsWith(split[0]);
            }
            return str.startsWith(split[0]) && str.endsWith(split[1]);
        }
        //*qwer*qwer; q*wer*qwer; *qwerqwer*; qwe*rqwer*;

        if (split[0].isEmpty()) {
            if (split[2].equals("$")) {
                return str.contains(split[1]); // *qwerqwer*
            } else {
                return str.contains(split[1]) && str.endsWith(split[2]); // *qwer*qwer
            }
        } else if (split[2].equals("$")) {
            return str.startsWith(split[0]) && str.contains(split[1]); // qwe*rqwer*;
        } else {
            return str.startsWith(split[0]) &&
                    str.contains(split[1]) &&
                    str.endsWith(split[2]);
        }
    }

    public static void main(String[] args) {
        StringFilter filter = new StringFilterImpl();

        for (String s : "proprietary, protection, programs, program, protect, problems".split(", ")) {
            filter.add(s);
        }
        Iterator<String> stringsByPattern = filter.getStringsByPattern("pro*");
        while (stringsByPattern.hasNext()) {
            System.out.println(stringsByPattern.next());
        }
    }
}
