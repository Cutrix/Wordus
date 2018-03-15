package com.wordus.tests;

import com.wordus.essentials.Dico;

import org.junit.Test;

import static org.junit.Assert.*;

public class DicoTest{
    @Test
    public void DetectWordTest() {

        Dico test= new Dico();
       String word1= "bidi";
       String word2= "bidi";
       assertEquals(test.detectWord(word1),test.detectWord(word2));
    }




}