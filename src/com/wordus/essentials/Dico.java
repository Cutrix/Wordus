package com.wordus.essentials;

import java.io.*;
import java.util.Optional;

public class Dico {

    private String[] words;
    private String lang;
    private String dico;
    private FileWriter fw;
    private PrintWriter pw;

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
        try {
            InputStream flux = new FileInputStream(PATH_DICO+dico);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;
            while ((ligne = buff.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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