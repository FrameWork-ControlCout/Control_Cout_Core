/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

//import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.dto.BonReceptionDTO;  
import com.FrameWork.ControlCout.Achat.Edition.BonReceptionEdition;
import com.FrameWork.ControlCout.Achat.factory.BonReceptionFactory;
import com.FrameWork.ControlCout.Achat.service.BonReceptionService;
import com.FrameWork.ControlCout.Parametrage.dto.SocDTO;
import com.FrameWork.ControlCout.Parametrage.dto.SocEditionDTO;
import com.FrameWork.ControlCout.Parametrage.service.SocService;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.crystaldecisions.sdk.occa.report.application.ParameterFieldController;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import jakarta.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
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
@RequestMapping("/api/achat/")
public class BonReceptionRessource {

    
    
    private final BonReceptionService bonReceptionService;
   private final SocService socService;

    public BonReceptionRessource(BonReceptionService bonReceptionService, SocService socService) {
        this.bonReceptionService = bonReceptionService;
        this.socService = socService;
    }

  
    
    @GetMapping("bon_reception/{code}")
    public ResponseEntity<BonReceptionDTO> getBonReceptionByCode(@PathVariable Integer code) {
        BonReceptionDTO dTO = bonReceptionService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("bon_reception/all")
    public ResponseEntity<List<BonReceptionDTO>> getAllBonReception() {
        return ResponseEntity.ok().body(bonReceptionService.findAllBonReception());
    }
 
        @GetMapping("bon_reception/findByHaveFBR")
    public ResponseEntity<List<BonReceptionDTO>> getAllBonReceptionByHaveFBR(@RequestParam Boolean haveFBR) {
        return ResponseEntity.ok().body(bonReceptionService.findAllBonReceptionByHaveFRB(haveFBR));
    }
 
     
        @GetMapping("bon_reception/findByHaveFBRAndCodeFournisseur")
    public ResponseEntity<List<BonReceptionDTO>> getAllBonReceptionByHaveFBRAndCodeFournisseur(@RequestParam Boolean haveFBR,@RequestParam Integer codeFournisseur) {
        return ResponseEntity.ok().body(bonReceptionService.findAllBonReceptionByHaveFRBAndCodeFournisseur(haveFBR,codeFournisseur));
    }
 
    
    @PostMapping("bon_reception")
    public ResponseEntity<BonReceptionDTO> postBonReception(@Valid @RequestBody BonReceptionDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        BonReceptionDTO result = bonReceptionService.save(dTO);
        return ResponseEntity.created(new URI("/api/achat/" + result.getCode())).body(result);
    }

    @PutMapping("bon_reception/update")
    public ResponseEntity<BonReceptionDTO> updateBonReception(@Valid @RequestBody BonReceptionDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        BonReceptionDTO result = bonReceptionService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("bon_reception/delete/{Code}")
    public ResponseEntity<BonReception> deleteBonReception(@PathVariable("Code") Integer code) {
        bonReceptionService.deleteBonReception(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
      @GetMapping("bon_reception/edition")
    public ResponseEntity<byte[]> editionBonReceptionWithDetails(@RequestParam(name = "code") Integer code) throws ReportSDKException, IOException, SQLException {
        List<BonReception> bonReceptions = bonReceptionService.findOneByCodeEdition(code);
       Collection<BonReceptionEdition> reportData = BonReceptionFactory.flattenBonReceptionForEdition(bonReceptions);
        SocDTO socDTO = socService.findOne(1);
        Blob blob = new SerialBlob(socDTO.getLogo());
        SocEditionDTO socEditionDTO = new SocEditionDTO(socDTO.getCode(), blob, socDTO.getNomSociete(), socDTO.getNomSocieteAr());
        ReportClientDocument reportClientDoc = new ReportClientDocument();
        reportClientDoc.open("Reports/BonReception.rpt", 0); 
        reportClientDoc.getDatabaseController().setDataSource(reportData, BonReceptionEdition.class, "Commande", "Commande"); 
        reportClientDoc.getSubreportController()
                .getSubreport("entete.rpt")
                .getDatabaseController()
                .setDataSource(
                        java.util.Arrays.asList(socEditionDTO),
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
