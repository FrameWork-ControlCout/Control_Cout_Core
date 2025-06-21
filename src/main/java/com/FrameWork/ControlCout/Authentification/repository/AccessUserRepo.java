/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Authentification.repository;

import com.FrameWork.ControlCout.Authentification.domaine.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface AccessUserRepo extends JpaRepository<User, Long> {

    User findFirstBy();
//    AccessUser findAccessUserByUserName(String UserName);

    User findUserByUserName(String userName);

}
