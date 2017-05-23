package com.example.arnaud.applicationgsb;

/**
 * Created by Arnaud on 10/11/2016.
 */

public class GlycemieInsuline {

        private double glycemieInf;
        private double glycemieSup;
        private int insuline;

        //Constructeur
        public GlycemieInsuline(double uneGlycemieInf, double uneGlycemieSup, int uneInsuline)
        {
            this.glycemieInf = uneGlycemieInf;
            this.glycemieSup = uneGlycemieSup;
            this.insuline = uneInsuline;
        }
        //Accesseurs
        public double getGlycemieInf()	{
            return this.glycemieInf;
        }
        public double getGlycemieSup()	{
            return this.glycemieSup;
        }
        public int getInsuline()	{
            return this.insuline;
        }

    }

