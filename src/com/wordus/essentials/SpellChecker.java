package com.wordus.essentials;

public class SpellChecker {

    //Passage de dico par DI (Dependency Injection)

    private Dico dc;
    private String likelyWords;

    public SpellChecker(Dico dc) {

        this.dc = dc;

    }

    public String getWordsStartBy(String word) {
        String likelyWord = getDico().getWords();
        String concatLikelyWord = "";
        for (String s : likelyWord.split(" ")) {
            if (s.startsWith(word)) {
                concatLikelyWord += " "+s;
            }
        }
        return concatLikelyWord;
    }


    //Getters
    public Dico getDico() {
        return dc;
    }

}
