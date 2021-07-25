package ru.skillbench.tasks.text;
import java.io.PrintStream;
import java.util.*;

public class WordCounterImpl implements WordCounter {
    String text = null;

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Map<String, Long> getWordCounts() {
        if (text == null) {
            throw new IllegalStateException();
        }

        Map<String, Long> res = new HashMap<>();
        for (String s : text.split("\\s")) {
            s = s.trim().toLowerCase();
            if(s.isEmpty()){
                continue;
            }
            if (s.startsWith("<") && s.endsWith(">")) {
                continue;
            }
            long count = res.getOrDefault(s, 0L);
            res.put(s, count + 1);
        }
        return res;
    }

    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() {
        return sort(getWordCounts(), Map.Entry.comparingByValue(Comparator.reverseOrder()));
    }

    @Override
    public <K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K, V>> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(comparator);
        return list;
    }

    @Override
    public <K, V> void print(List<Map.Entry<K, V>> entries, PrintStream ps) {
        entries.forEach(kvEntry -> {
            ps.println(kvEntry.getKey().toString().toLowerCase() + " " + kvEntry.getValue().toString().toLowerCase());
        });
    }
}
