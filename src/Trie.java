import java.util.HashMap;
import java.util.Map;

public class Trie {
    Map<Character, Trie> children;
    boolean isKey;

    public Trie() {
        this.children = new HashMap<>();
        this.isKey = false;
    }

    public void insert(String word) {
        char ch = word.charAt(0);

        if (!children.containsKey(ch)) {
            children.put(ch, new Trie());
        }
        if (word.length() == 1) {
            children.get(ch).isKey = true;
        } else {
            children.get(ch).insert(word.substring(1));
        }
    }

    public boolean search(String word) {
        char ch = word.charAt(0);

        if (!children.containsKey(ch)) {
            return false;
        }

        if (word.length() == 1) {
            return children.get(ch).isKey;
        }

        return children.get(ch).search(word.substring(1));
    }

    public boolean startsWith(String prefix) {
        char ch = prefix.charAt(0);

        if (!children.containsKey(ch)) {
            return false;
        }

        if (prefix.length() == 1) {
            return true;
        }

        return children.get(ch).startsWith(prefix.substring(1));
    }
}
/*
Trie {
    Map<Character,Trie> dataMap,
    char value,
    boolean isKey
}


*/