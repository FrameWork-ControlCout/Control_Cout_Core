/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

//import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.ControlCout.Achat.Edition.BonReceptionEdition;
import com.FrameWork.ControlCout.Achat.Edition.FactureBonReceptionEdition;
import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.FactureBonReception;
import com.FrameWork.ControlCout.Achat.dto.FactureBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.OrderAchatDTO;
import com.FrameWork.ControlCout.Achat.factory.BonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.FactureBonReceptionFactory;
import com.FrameWork.ControlCout.Achat.service.FactureBonReceptionService;
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
import java.time.LocalDate;
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
public class FactureBonReceptionRessource {

    private final FactureBonReceptionService factureBonReceptionService;
    private final SocService socService;

    public FactureBonReceptionRessource(FactureBonReceptionService factureBonReceptionService, SocService socService) {
        this.factureBonReceptionService = factureBonReceptionService;
        this.socService = socService;
    }

    @GetMapping("facture_bon_reception/{code}")
    public ResponseEntity<FactureBonReceptionDTO> getFactureBonReceptionByCode(@PathVariable Integer code) {
        FactureBonReceptionDTO dTO = factureBonReceptionService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("facture_bon_reception/all")
    public ResponseEntity<List<FactureBonReceptionDTO>> getAllFactureBonReception() {
        return ResponseEntity.ok().body(factureBonReceptionService.findAllFactureBonReception());
    }

    @GetMapping("facture_bon_reception/findByFournisseur")
    public ResponseEntity<List<FactureBonReceptionDTO>> getAllFactureBonReceptionByFournisseur(@RequestParam Integer codeFournisseur) {
        return ResponseEntity.ok().body(factureBonReceptionService.findFactureBonReceptionByCodeFournisseur(codeFournisseur));
    }

    @GetMapping("facture_bon_reception/findByDate")
    public ResponseEntity<Collection<FactureBonReceptionDTO>> getAllFactureBonReceptionByDateCreate(@RequestParam LocalDate dateDebut, @RequestParam LocalDate dateFin) {
        return ResponseEntity.ok().body(factureBonReceptionService.findFactureBonReceptionByDateCreateBetween(dateDebut, dateFin));
    }

    @PostMapping("facture_bon_reception")
    public ResponseEntity<FactureBonReceptionDTO> postFactureBonReception(@Valid @RequestBody FactureBonReceptionDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FactureBonReceptionDTO result = factureBonReceptionService.save(dTO);
        return ResponseEntity.created(new URI("/api/achat/" + result.getCode())).body(result);
    }

    @PutMapping("facture_bon_reception/update")
    public ResponseEntity<FactureBonReceptionDTO> updateFactureBonReception(@Valid @RequestBody FactureBonReceptionDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        FactureBonReceptionDTO result = factureBonReceptionService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("facture_bon_reception/delete/{Code}")
    public ResponseEntity<FactureBonReception> deleteFactureBonReception(@PathVariable("Code") Integer code) {
        factureBonReceptionService.deleteFactureBonReception(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("facture_bon_reception/edition")
    public ResponseEntity<byte[]> editionBonReceptionWithDetails(@RequestParam(name = "code") Integer code) throws ReportSDKException, IOException, SQLException {
        List<FactureBonReception> factureBonReceptions = factureBonReceptionService.findOneByCodeEdition(code);
        Collection<FactureBonReceptionEdition> reportData = FactureBonReceptionFactory.flattenFactueBonReceptionForEdition(factureBonReceptions);
        SocDTO socDTO = socService.findOne(1);
        Blob blob = new SerialBlob(socDTO.getLogo());
        SocEditionDTO socEditionDTO = new SocEditionDTO(socDTO.getCode(), blob, socDTO.getNomSociete(), socDTO.getNomSocieteAr());
        ReportClientDocument reportClientDoc = new ReportClientDocument();
        reportClientDoc.open("Reports/FactureBonReception.rpt", 0);
        reportClientDoc.getDatabaseController().setDataSource(reportData, FactureBonReceptionEdition.class, "Commande", "Commande");
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
