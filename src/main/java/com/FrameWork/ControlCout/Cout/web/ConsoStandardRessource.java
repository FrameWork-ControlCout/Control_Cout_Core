/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.web;

import com.FrameWork.ControlCout.Cout.Edition.ConsoStandardEdition;
import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import com.FrameWork.ControlCout.Cout.dto.ConsoStandardDTO;
import com.FrameWork.ControlCout.Cout.dto.DetailsConsoStandardDTO;
import com.FrameWork.ControlCout.Cout.dto.DetailsConsoStandardPerDayDTO;
import com.FrameWork.ControlCout.Cout.factory.ConsoStandardFactory;
import com.FrameWork.ControlCout.Cout.service.ConsoStandardService;
import com.FrameWork.ControlCout.Cout.service.DetailsConsoStandardPerDayService;
import com.FrameWork.ControlCout.Cout.service.DetailsConsoStandardService;
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
import java.util.stream.Collectors;
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
@RequestMapping("/api/cout/")
public class ConsoStandardRessource {

    private final ConsoStandardService consoStandardService;
    private final SocService socService;
    private final DetailsConsoStandardService detailsConsoStandardService;
        private final DetailsConsoStandardPerDayService detailsConsoStandardPerDayService;

    public ConsoStandardRessource(ConsoStandardService consoStandardService, SocService socService, DetailsConsoStandardService detailsConsoStandardService, DetailsConsoStandardPerDayService detailsConsoStandardPerDayService) {
        this.consoStandardService = consoStandardService;
        this.socService = socService;
        this.detailsConsoStandardService = detailsConsoStandardService;
        this.detailsConsoStandardPerDayService = detailsConsoStandardPerDayService;
    }


    

    @GetMapping("consommation_standard/{code}")
    public ResponseEntity<ConsoStandardDTO> getConsoStandardByCode(@PathVariable Integer code) {
        ConsoStandardDTO dTO = consoStandardService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("consommation_standard/all")
    public ResponseEntity<List<ConsoStandardDTO>> getAllConsoStandard() {
        return ResponseEntity.ok().body(consoStandardService.findAllConsoStandard());
    }

    @GetMapping("consommation_standard/findByActif")
    public ResponseEntity<List<ConsoStandardDTO>> getAllConsoStandardByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(consoStandardService.findAllConsoStandardByActif(actif));
    }

    @GetMapping("consommation_standard/findByActifAndCodeDepot")
    public ResponseEntity<List<ConsoStandardDTO>> getAllConsoStandardByActifAndCodeDepot(@RequestParam Boolean actif, @RequestParam Integer codeDepot) {
        return ResponseEntity.ok().body(consoStandardService.findAllConsoStandardByActifAndCodeDepot(actif, codeDepot));
    }

    @PostMapping("consommation_standard")
    public ResponseEntity<ConsoStandardDTO> postConsoStandard(@Valid @RequestBody ConsoStandardDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        ConsoStandardDTO result = consoStandardService.save(dTO);
        return ResponseEntity.created(new URI("/api/cout/" + result.getCode())).body(result);
    }

    @PutMapping("consommation_standard/update")
    public ResponseEntity<ConsoStandardDTO> updateConsoStandard(@Valid @RequestBody ConsoStandardDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        ConsoStandardDTO result = consoStandardService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("consommation_standard/delete/{Code}")
    public ResponseEntity<ConsoStandard> deleteConsoStandard(@PathVariable("Code") Integer code) {
        consoStandardService.deleteConsoStandard(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("consommation_standard/edition")
    public ResponseEntity<byte[]> editionConsoStandardWithDetails(@RequestParam(name = "code") List<Integer> code) throws ReportSDKException, IOException, SQLException {
        List<ConsoStandard> consoStandards = consoStandardService.findOneByCodeEdition(code);
        Collection<ConsoStandardEdition> reportData = ConsoStandardFactory.flattenConsoStandardForEdition(consoStandards);
        SocDTO socDTO = socService.findOne(1);
        Blob blob = new SerialBlob(socDTO.getLogo());

        String allCodesSaisie = consoStandards.stream()
                .map(ConsoStandard::getCodeSaisie) // Extracts the codeSaisie string from each object
                .distinct() // Optional: Removes duplicate codes
                .collect(Collectors.joining(", ")); // Joins them with a comma and space

        SocEditionDTO socEditionDTO = new SocEditionDTO(socDTO.getCode(), blob, socDTO.getNomSociete(), socDTO.getNomSocieteAr());
        ReportClientDocument reportClientDoc = new ReportClientDocument();
        reportClientDoc.open("Reports/ConsoStandardGrouped.rpt", 0);
        reportClientDoc.getDatabaseController().setDataSource(reportData, ConsoStandardEdition.class, "Commande", "Commande");
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
        paramController.setCurrentValue("", "codeSaisieCS", allCodesSaisie);
        ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream) reportClientDoc.getPrintOutputController().export(ReportExportFormat.PDF);
        reportClientDoc.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return ResponseEntity.ok().headers(headers).body(Helper.read(byteArrayInputStream));
    }

   // NEW ENDPOINT 1
    @GetMapping("consommation_standard/{codeConsoStandard}/details")
    public ResponseEntity<List<DetailsConsoStandardDTO>> getDetailsByConsoStandard(@PathVariable List<Integer> codeConsoStandard) {
        List<DetailsConsoStandardDTO> details = detailsConsoStandardService.findByCodeConsoStandard(codeConsoStandard);
        return ResponseEntity.ok(details);
    }
    
    // NEW ENDPOINT 2
    @GetMapping("consommation_standard/{codeConsoStandard}/details_per_day")
    public ResponseEntity<List<DetailsConsoStandardPerDayDTO>> getDetailsPerDayByConsoStandard(@PathVariable Integer codeConsoStandard) {
        List<DetailsConsoStandardPerDayDTO> details = detailsConsoStandardPerDayService.findByCodeConsoStandard(codeConsoStandard);
        return ResponseEntity.ok(details);
    }

    
}
