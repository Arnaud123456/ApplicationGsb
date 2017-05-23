package com.example.arnaud.applicationgsb;

import java.util.ArrayList;

/**
 * Created by Arnaud on 10/11/2016.
 */

public class Protocole {


        private int numeroProtocole;
        private ArrayList<GlycemieInsuline> lesGlycemieInsuline;

        public Protocole(int unNumero)	{
            this.numeroProtocole = unNumero;
            lesGlycemieInsuline = new ArrayList<GlycemieInsuline>();
        }

        public int getNumeroProtocole()	{
            return this.numeroProtocole;
        }

        public void ajouter(GlycemieInsuline uneGlycemieInsuline)	{
            this.lesGlycemieInsuline.add(uneGlycemieInsuline);
        }

        public int insuline(double uneGlycemie)	{
            int retour = 0;
            for (GlycemieInsuline a : lesGlycemieInsuline){


            if ( uneGlycemie>=a.getGlycemieInf() && uneGlycemie<=a.getGlycemieSup())
               retour = a.getInsuline();
            }


            //Renvoie le nombre d'unités d'insuline en fonction de la glycémie
            return retour;
        }
    }

