/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.domaine;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Administrator
 */
@Embeddable
public class DetailsAlimentationCaissePK {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Alimentation_Caisse", nullable = false)
    private int codeAlimentationCaisse;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Type_Recette", nullable = false)
    private int codeTypeRecette;

    public DetailsAlimentationCaissePK() {
    }

    public int getCodeAlimentationCaisse() {
        return codeAlimentationCaisse;
    }

    public void setCodeAlimentationCaisse(int codeAlimentationCaisse) {
        this.codeAlimentationCaisse = codeAlimentationCaisse;
    }

    public int getCodeTypeRecette() {
        return codeTypeRecette;
    }

    public void setCodeTypeRecette(int codeTypeRecette) {
        this.codeTypeRecette = codeTypeRecette;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.codeAlimentationCaisse;
        hash = 59 * hash + this.codeTypeRecette;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetailsAlimentationCaissePK other = (DetailsAlimentationCaissePK) obj;
        if (this.codeAlimentationCaisse != other.codeAlimentationCaisse) {
            return false;
        }
        return this.codeTypeRecette == other.codeTypeRecette;
    }
    
    

}
