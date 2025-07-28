/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Config;

/**
 *
 * @author Administrator
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

@Component
public class FontRegister {

    private static final Logger log = LoggerFactory.getLogger(FontRegister.class);

//    @EventListener(ApplicationReadyEvent.class)
//    public void registerFonts() {
//        log.info("Starting to register custom fonts...");
//        try {
//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//
//            // Find all font files
//            Resource[] fontResources = resolver.getResources("classpath:fonts/*.ttf");
//
//            if (fontResources.length == 0) {
//                log.error("No custom fonts found in 'resources/fonts/'. This may cause PDF export issues.");
//                return;
//            }
//
//            boolean anyFontRegistered = false;
//            for (Resource fontResource : fontResources) {
//                try {
//                    Font font = Font.createFont(Font.TRUETYPE_FONT, fontResource.getInputStream());
//                    boolean registered = ge.registerFont(font);
//                    if (registered) {
//                        anyFontRegistered = true;
//                        log.info("Successfully registered font: {}", font.getFontName());
//                    } else {
//                        log.warn("Font registration failed for: {}", fontResource.getFilename());
//                    }
//                } catch (FontFormatException | IOException e) {
//                    log.error("Failed to register font from file: {}", fontResource.getFilename(), e);
//                }
//            }
//
//            if (!anyFontRegistered) {
//                log.error("No fonts were successfully registered. PDF export may fail.");
//            } else {
//                log.info("Font registration completed.");
//            }
//
//        } catch (IOException e) {
//            log.error("Could not read fonts from classpath.", e);
//        } catch (Exception e) {
//            log.error("Unexpected error during font registration", e);
//        }
//    }
    @EventListener(ApplicationReadyEvent.class)
    public void registerFonts() {
        log.info("Starting font registration...");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // List of standard fonts that work well with Crystal Reports
        String[] standardFonts = {
            "Arial", "Arial Unicode MS", "Times New Roman",
            "Courier New", "Verdana", "Tahoma"
        };

        for (String fontName : standardFonts) {
            try {
                Font font = new Font(fontName, Font.PLAIN, 12);
                if (ge.registerFont(font)) {
                    log.info("Registered standard font: {}", fontName);
                }
            } catch (Exception e) {
                log.warn("Could not register font {}: {}", fontName, e.getMessage());
            }
        }
    }
}
