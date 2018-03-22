package com.wordus.essentials;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Dico {

    private String[] words;
    private String lang;
    private String dico;
    private FileWriter fw;
    private PrintWriter pw;
    private String[] allWords;

    final static String PATH_DICO = "dico/";

    public Dico() {
        this.lang = "Fr";
        this.dico  = "dico_fr.txt";
    }

    public Dico(String lg, String dico) {
        this.lang = lg;
        this.dico = dico;
    }

    /**
     * Ajoute des mots dans le dictionnaire
     * @param word
     * @return void
     */
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

    /**
     * Permet de supprimer les mots dans un dictionnaire
     * @param word
     * @return void
     */
    public void del(String word) {
        //Verifier si word est dans le dico
        if (detectWord(word)) {
            ArrayList<String> al = new ArrayList<String>(Arrays.asList(this.getAllWords()));
            al.remove(word);
            String[] newDico = new String[al.size()];
            newDico = al.toArray(newDico);
            try {
                fw = new FileWriter(PATH_DICO+dico);
                for (String s : newDico) {
                    fw.write(s+" ");
                }
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    public void update(String word, String replaceW) {
        //System.out.print("modification");
        if (detectWord(word)) {
            int indexWord;
            ArrayList<String> al = new ArrayList<String>(Arrays.asList(this.getAllWords()));
            indexWord = al.indexOf(word);
            al.set(indexWord, replaceW);

            String[] newDico = new String[al.size()];
            newDico = al.toArray(newDico);
            try {
                fw = new FileWriter(PATH_DICO+dico);
                for (String s : newDico) {
                    fw.write(s+" ");
                }
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    /**
     * Verifie si le mot se trouve dans le fichier
     * @param word
     * @return bool
     */
    public boolean detectWord(String word) {
        String words = this.getWords();
        allWords = words.split(" ");
        for (String s : allWords) {
            if (word.contentEquals(s)) return true;
        }
        return false;
    }

//Gettters et setters
    public String getWords() {
        String word = null;
        try {
            InputStream flux = new FileInputStream(PATH_DICO+dico);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;
            while ((ligne = buff.readLine()) != null) {
                word += ligne;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return word;
    }

    public String[] getAllWords() {
        return allWords;
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