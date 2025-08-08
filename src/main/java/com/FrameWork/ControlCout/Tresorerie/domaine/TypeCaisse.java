/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.domaine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Type_Caisse", schema = "tresorerie")
@Audited
@AuditTable("Type_Caisse_AUD")
@Getter
@Setter
public class TypeCaisse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;  

    @Size(max = 200)
    @Column(name = "Designation_Ar", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String designationAr;

    @Size(max = 200)
    @Column(name = "Designation_Lt", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String designationLt;
    
     @Size(max = 2)
    @Column(name = "Type", length = 2, nullable = false, columnDefinition = "nvarchar(2)")
    private String type;

    public TypeCaisse() {
    }

    
    
    
    
}
