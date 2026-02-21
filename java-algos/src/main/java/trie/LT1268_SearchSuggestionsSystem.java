package trie;

import java.util.*;

public class LT1268_SearchSuggestionsSystem {
    //================================BRUTE FORCE============================================
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Queue<String> availableWords;
        StringBuilder sb = new StringBuilder();
        List<List<String>> result = new ArrayList<>();


        for(int i = 0; i < searchWord.length(); i++){
            sb.append(searchWord.charAt(i));
            List<String> curWords = new ArrayList<>();
            availableWords = new PriorityQueue<>();

            for(String product : products){
                if(product.startsWith(String.valueOf(sb))){
                    availableWords.add(product);
                }
            }

            int maxWords = Math.min(3, availableWords.size());

            for(int j = 0; j < maxWords; j++){
                curWords.add(availableWords.poll());
            }

            result.add(curWords);
        }

        return result;
    }

    //===============================TRIE===================================

    private static class TrieNode{
        TrieNode[] children = new TrieNode[26];
        List<String> accaptedWords = new ArrayList<>();
    }


    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        Arrays.sort(products);

        List<List<String>> result = new ArrayList<>();

        //Build the Trie: O(N * word length)
        for(String product : products){
            TrieNode cur = root;
            for(char ch : product.toCharArray()){
                int idx = ch - 'a';

                if(cur.children[idx] == null){
                    cur.children[idx] = new TrieNode();
                }
                cur = cur.children[idx];

                if(cur.accaptedWords.size() < 3){
                    cur.accaptedWords.add(product);
                }
            }
        }

        TrieNode cur = root;
        for(int i = 0; i < searchWord.length(); i++){
            int idx = searchWord.charAt(i) - 'a';
            cur = cur.children[idx];
            if(cur == null){
                while(i < searchWord.length()){
                    result.add(List.of());
                    i++;
                }
                break;
            }

            result.add(cur.accaptedWords);
        }

        return result;
    }
}
