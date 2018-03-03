package com.wordus.essentials;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class Dico {

    private String[] words;
    private String lang;
    private String dico;
    private FileWriter fw;

    final static String PATH_DICO = "dico/";

    public Dico() {
        this.lang = "Fr";
        this.dico  = "dico_fr.txt";
    }

    public Dico(String lg, String dico) {
        this.lang = lg;
        this.dico = dico;
    }

    public void add(String word) {
        //System.out.println(this.dico);
        File f = new File(PATH_DICO+this.dico);
        try {
            fw = new FileWriter(f, true);
            fw.write(word+" ");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//Gettters et setters
    public void getWords() {

    }

    public String getDico() {
        return dico;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setDico(String dico) {
        this.dico = dico;
    }
}