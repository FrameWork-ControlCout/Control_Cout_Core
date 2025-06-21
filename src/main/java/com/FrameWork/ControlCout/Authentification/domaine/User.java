/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Authentification.domaine;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;


@Table(name = "Users", schema = "access")
@Entity
@Audited
@AuditTable("users_AUD")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false, name = "Full_Name", columnDefinition = "Nvarchar(200)")
    private String fullName;

    @Column(unique = true, nullable = false, name = "User_Name", columnDefinition = "Nvarchar(200)")
    private String userName;

    @Column(length = 100, name = "Email", columnDefinition = "varchar(100)")
    private String email;

    @Column(nullable = false, name = "Pass_cry")
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "Created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "Updated_at")
    private Date updatedAt;

    @Lob
    @Column(name = "Signature",columnDefinition = ("varbinary(MAX)"))
    private byte[] signature;
    
        @Lob
    @Column(name = "Image_Profil",columnDefinition = ("varbinary(MAX)"))
    private byte[] imageProfil;

    @Column(nullable = false, name = "Password")
    private String passwordDecry;
    
       @Column( name = "Code_Medecin" )
    private Integer codeMedecin;
       
          @Column( name = "Permission_DMI")
    private String permissionDMI;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public User setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public User setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

//    @Override
//    public String toString() {
//        return "User{" + "id=" + id + ", fullName=" + fullName + ", userName=" + userName + ", email=" + email + ", password=" + password + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
//    }
    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fullName=" + fullName + ", userName=" + userName + ", email=" + email + ", password=" + password + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", signature=" + signature + ", passwordDecry=" + passwordDecry + '}';
    }

  

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getPasswordDecry() {
        return passwordDecry;
    }

    public void setPasswordDecry(String passwordDecry) {
        this.passwordDecry = passwordDecry;
    }

    public Integer getCodeMedecin() {
        return codeMedecin;
    }

    public void setCodeMedecin(Integer codeMedecin) {
        this.codeMedecin = codeMedecin;
    }

    public String getPermissionDMI() {
        return permissionDMI;
    }

    public void setPermissionDMI(String permissionDMI) {
        this.permissionDMI = permissionDMI;
    }

    public byte[] getImageProfil() {
        return imageProfil;
    }

    public void setImageProfil(byte[] imageProfil) {
        this.imageProfil = imageProfil;
    }
    
    

}
