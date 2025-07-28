/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleEdition;
import com.FrameWork.ControlCout.Parametrage.dto.SocDTO;
import com.FrameWork.ControlCout.Parametrage.dto.SocEditionDTO;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.service.ArticleService;
import com.FrameWork.ControlCout.Parametrage.service.SocService;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.crystaldecisions.sdk.occa.report.application.ParameterFieldController;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import com.crystaldecisions.sdk.occa.report.exportoptions.ExportOptions;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;

//import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
//import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException; 
//import com.crystaldecisions12.sdk.occa.report.exportoptions.ReportExportFormat;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.sql.Blob;
//import java.sql.SQLException;
//import java.util.Hashtable;
import java.io.ByteArrayOutputStream;

import jakarta.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage_achat/")
public class ArticleRessource {

    private static final Logger log = LoggerFactory.getLogger(ArticleRessource.class);
    private final ArticleService articleService;
    private final SocService socService;

    public ArticleRessource(ArticleService articleService, SocService socService) {
        this.articleService = articleService;
        this.socService = socService;
    }

    @GetMapping("article/{code}")
    public ResponseEntity<ArticleDTO> getArticleByCode(@PathVariable Integer code) {
        ArticleDTO dto = articleService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("article/all")
    public ResponseEntity<List<ArticleDTO>> getAllArticle() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(articleService.findAllArticle());
    }

    @GetMapping("article/findByTypeAndActif")
    public ResponseEntity<List<ArticleDTO>> getArticleByTypeAndActif(@RequestParam String type, @RequestParam Boolean actif) {
        return ResponseEntity.ok().body(articleService.findAllArticleByTypeAndActif(type, actif));
    }

    @GetMapping("article/findByType")
    public ResponseEntity<List<ArticleDTO>> getArticleByType(@RequestParam String type) {
        return ResponseEntity.ok().body(articleService.findAllArticleByType(type));
    }

    @PostMapping("article")
    public ResponseEntity<ArticleDTO> postArticle(@Valid @RequestBody ArticleDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        ArticleDTO result = articleService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage_achat/" + result.getCode())).body(result);
    }

    @PutMapping("article/update")
    public ResponseEntity<Article> updateArticle(@RequestBody @Valid ArticleDTO dto) throws URISyntaxException {
        Article result = articleService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("article/delete/{Code}")
    public ResponseEntity<Article> deleteArticle(@PathVariable("Code") Integer code) {
        articleService.deleteArticle(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("article/edition")
    public ResponseEntity<byte[]> editionArticle(@RequestParam(name = "actif") Boolean actif) throws ReportSDKException, IOException, SQLException {

        List<Article> articles = articleService.findAllArticleByTypeAndActifEdition("ECO", actif);
        Collection<ArticleEdition> pricesEdition = ArticleFactory.listArticleTOEditionArticle(articles);
        SocDTO socDTO = socService.findOne(1);
        Blob blob = new SerialBlob(socDTO.getLogo());
        SocEditionDTO cliniqueEdition = new SocEditionDTO(socDTO.getCode(), blob, socDTO.getNomSociete(), socDTO.getNomSocieteAr());
        ReportClientDocument reportClientDoc = new ReportClientDocument();
        reportClientDoc.open("Reports/ArticleEC.rpt", 0);  
        reportClientDoc.getDatabaseController().setDataSource(pricesEdition, ArticleEdition.class, "Commande", "Commande"); 
        reportClientDoc.getSubreportController()
                .getSubreport("entete.rpt")
                .getDatabaseController()
                .setDataSource(
                        java.util.Arrays.asList(cliniqueEdition),
                        SocEditionDTO.class,
                        "societe",
                        "societe"
                ); 
        ParameterFieldController paramController = reportClientDoc.getDataDefController().getParameterFieldController();
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        paramController.setCurrentValue("", "user", user);
        ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream) reportClientDoc.getPrintOutputController().export(ReportExportFormat.PDF);
        reportClientDoc.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return ResponseEntity.ok().headers(headers).body(Helper.read(byteArrayInputStream));
    }

}
