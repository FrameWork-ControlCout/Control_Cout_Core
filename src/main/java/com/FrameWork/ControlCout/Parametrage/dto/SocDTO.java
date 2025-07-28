package com.FrameWork.ControlCout.Parametrage.dto;
 

import com.FrameWork.ControlCout.Achat.dto.*;
import java.lang.Integer;
import java.lang.String;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SocDTO {

    @NotNull
    private Integer code;

    private byte[] logo;

    @NotNull
    @Size(
            min = 1,
            max = 100
    )
    private String nomSociete;

    @Size(
            max = 100
    )
    private String nomSocieteAr;

    private String imagePath;
    private Integer codeSite;
    private Integer codeCompany;
    
    private String registrationNumber;//id company company  ra9m tasjil
    private String taxpayerActivityCode;// company كود النشاط الضريبي
    private String governate;//
    private String regionCity;//
    private String street;//
    private String buildingNumber;//
    private Integer taxpayerBranchID;//
    private Integer codeGovernate;
    private Integer codeSiteOfCompany;
    private String companyIpAdress;

    private byte[] imgIso;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getNomSocieteAr() {
        return nomSocieteAr;
    }

    public void setNomSocieteAr(String nomSocieteAr) {
        this.nomSocieteAr = nomSocieteAr;
    }

   
    

    public Integer getCodeSite() {
        return codeSite;
    }

    public void setCodeSite(Integer codeSite) {
        this.codeSite = codeSite;
    }

    public Integer getCodeCompany() {
        return codeCompany;
    }

    public void setCodeCompany(Integer codeCompany) {
        this.codeCompany = codeCompany;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getTaxpayerActivityCode() {
        return taxpayerActivityCode;
    }

    public void setTaxpayerActivityCode(String taxpayerActivityCode) {
        this.taxpayerActivityCode = taxpayerActivityCode;
    }

    public Integer getCodeGovernate() {
        return codeGovernate;
    }

    public void setCodeGovernate(Integer codeGovernate) {
        this.codeGovernate = codeGovernate;
    }

    public String getGovernate() {
        return governate;
    }

    public void setGovernate(String governate) {
        this.governate = governate;
    }

    public String getRegionCity() {
        return regionCity;
    }

    public void setRegionCity(String regionCity) {
        this.regionCity = regionCity;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getTaxpayerBranchID() {
        return taxpayerBranchID;
    }

    public void setTaxpayerBranchID(Integer taxpayerBranchID) {
        this.taxpayerBranchID = taxpayerBranchID;
    }

    public Integer getCodeSiteOfCompany() {
        return codeSiteOfCompany;
    }


    public void setCodeSiteOfCompany(Integer codeSiteOfCompany) {
        this.codeSiteOfCompany = codeSiteOfCompany;
    }

    public void setCompanyIpAdress(String companyIpAdress) {
        this.companyIpAdress = companyIpAdress;
    }

    public String getCompanyIpAdress() {
        return companyIpAdress;
    }

    public byte[] getImgIso() {
        return imgIso;
    }

    public void setImgIso(byte[] imgIso) {
        this.imgIso = imgIso;
    }
    
    
}
