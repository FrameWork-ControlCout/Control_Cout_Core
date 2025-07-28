/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.web;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.Edition.BonReceptionEdition;
import com.FrameWork.ControlCout.Achat.factory.BonReceptionFactory;
import com.FrameWork.ControlCout.Parametrage.dto.SocDTO;
import com.FrameWork.ControlCout.Parametrage.dto.SocEditionDTO;
import com.FrameWork.ControlCout.Parametrage.service.SocService;
import com.FrameWork.ControlCout.Stock.Edition.DepenseEdition;
import com.FrameWork.ControlCout.Stock.domaine.Depense;
import com.FrameWork.ControlCout.Stock.dto.DepenseDTO;
import com.FrameWork.ControlCout.Stock.factory.DepenseFactory;
import com.FrameWork.ControlCout.Stock.service.DepenseService;
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
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
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
@RequestMapping("/api/stock/")
public class DepenseRessource {

    private final DepenseService depenseService;
   private final SocService socService;

    public DepenseRessource(DepenseService depenseService, SocService socService) {
        this.depenseService = depenseService;
        this.socService = socService;
    }
     
    @GetMapping("depense/{code}")
    public ResponseEntity<DepenseDTO> getDepenseByCode(@PathVariable Integer code) {
        DepenseDTO dTO = depenseService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("depense/all")
    public ResponseEntity<List<DepenseDTO>> getAllDepense() {
        return ResponseEntity.ok().body(depenseService.findAllDepense());
    }

    @PostMapping("depense")
    public ResponseEntity<DepenseDTO> postDepense(@Valid @RequestBody DepenseDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        DepenseDTO result = depenseService.save(dTO);
        return ResponseEntity.created(new URI("/api/stock/" + result.getCode())).body(result);
    }

    @DeleteMapping("depense/delete/{Code}")
    public ResponseEntity<Depense> deleteDepense(@PathVariable("Code") Integer code) {
        depenseService.deleteDepense(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
     @GetMapping("depense/edition")
    public ResponseEntity<byte[]> editionBonReceptionWithDetails(@RequestParam(name = "code") Integer code) throws ReportSDKException, IOException, SQLException {
        List<Depense> depenses = depenseService.findOneByCodeEdition(code);
       Collection<DepenseEdition> reportData = DepenseFactory.flattenDepenseForEdition(depenses);
        SocDTO socDTO = socService.findOne(1);
        Blob blob = new SerialBlob(socDTO.getLogo());
        SocEditionDTO socEditionDTO = new SocEditionDTO(socDTO.getCode(), blob, socDTO.getNomSociete(), socDTO.getNomSocieteAr());
        ReportClientDocument reportClientDoc = new ReportClientDocument();
        reportClientDoc.open("Reports/Depense.rpt", 0); 
        reportClientDoc.getDatabaseController().setDataSource(reportData, DepenseEdition.class, "Commande", "Commande"); 
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
