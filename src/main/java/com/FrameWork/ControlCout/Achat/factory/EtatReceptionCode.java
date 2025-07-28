/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

/**
 *
 * @author Administrator
 */
public final class EtatReceptionCode {
    public static final Integer EN_COURS = 1;      // In Progress / Not Received
    public static final Integer PARTIELLEMENT_RECU = 2; // Partially Received
    public static final Integer COMPLETEMENT_RECU = 3;  // Completely Received

    private EtatReceptionCode() {} // Private constructor to prevent instantiation
}