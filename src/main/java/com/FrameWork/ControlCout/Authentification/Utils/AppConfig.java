 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Authentification.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Administrator
 */
//@Configuration
public class AppConfig {

    @Value("${spring.jackson.time-zone}")
    private String appTimeZone;

    @Bean
    public String appTimeZoneId() {  // Changed method name and return type
        return appTimeZone;
    }
}
