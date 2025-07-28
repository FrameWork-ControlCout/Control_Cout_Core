/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.constants;

/**
 *
 * @author Administrator
 */
public enum AchatConstants {
    TYPE_MVT_ENTREE_RECEPTION("ENTREE_RECEPTION"),
    TYPE_MVT_DISPENSATION("DISPENSATION"),
    CODE_ETAT_PAIEMENT_NON_PAYEE("3"),
    CODE_ETAT_PAIEMENT_PAYEE_PATRIEL("1"),
    CODE_ETAT_PAIEMENT_PAYEE("2"),
    CODE_ETAT_RECEPTION_PARTIEL("2"),
    CODE_ETAT_RECEPTION_TOTAL("3"),
    CODE_ETAT_RECEPTION_NON_RECEPTIONNER("1");

    private String name = "";

    //Constructeur
    AchatConstants(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
