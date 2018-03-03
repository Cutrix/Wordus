package com.wordus.essentials;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Dico {

    private String[] words;
    private String language;
    private String _word;
    private FileWriter fw;
    private String dictionnary;


    public Dico() {

    }

    public Dico(String word) {
        this._word = word;
    }

    public void add(String word) throws IOException {
        File f = new File(dictionnary);
        try {
            fw = new FileWriter(f);
            fw.write(word);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDictionnary(String lang) {
        switch (lang) {
            case "Francais":
                this.dictionnary = "dico_fr.txt";
                break;

            case "Anglais":
                this.dictionnary = "dico_ang.txt";
                break;
        }
    }

    public String getDictionnary() {
        return dictionnary;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String lang) {
        this.language = lang;
    }
}
