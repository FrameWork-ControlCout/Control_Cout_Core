/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.web;
 
import com.FrameWork.ControlCout.Parametrage.service.ParamService;
import com.FrameWork.ControlCout.Parametrage.service.SocService;
import com.FrameWork.ControlCout.Tresorerie.domaine.TransfertCaisse;
import com.FrameWork.ControlCout.Tresorerie.dto.TransfertCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.service.TransfertCaisseService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/recette/")
public class TransfertCaisseRessource {
    private final TransfertCaisseService transfertCaisseService;
    private final ParamService paramService;
    private final SocService societeService;

//    private final AccessUserService accessUserService;

    public TransfertCaisseRessource(TransfertCaisseService transfertCaisseService, ParamService paramService, SocService societeService) {
        this.transfertCaisseService = transfertCaisseService;
        this.paramService = paramService;
        this.societeService = societeService;
    }

   

    @GetMapping("transfert_caisse/{code}")
    public ResponseEntity<TransfertCaisseDTO> getTransfertCaisseByCode(@PathVariable Integer code) {
        TransfertCaisseDTO dTO = transfertCaisseService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("transfert_caisse/all")
    public ResponseEntity<List<TransfertCaisseDTO>> getAllTransfertCaisse() {
        return ResponseEntity.ok().body(transfertCaisseService.findAllTransfertCaisse());
    }

    

 

    @GetMapping("transfert_caisse/EtatApprouver/{codeEtatApprouver}")
    public ResponseEntity<List<TransfertCaisseDTO>> getAppelOffreByCodeEtatApprouve(@PathVariable Integer codeEtatApprouver) {
        List<TransfertCaisseDTO> dto = transfertCaisseService.findByEtatApprouver(codeEtatApprouver);
        return ResponseEntity.ok().body(dto);

    }

    @PostMapping("transfert_caisse")
    public ResponseEntity<TransfertCaisseDTO> postTransfertCaisse(@Valid @RequestBody TransfertCaisseDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TransfertCaisseDTO result = transfertCaisseService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

//    @PutMapping("transfert_caisse/update")
//    public ResponseEntity<TransfertCaisseDTO> updateTransfertCaisse(@Valid @RequestBody TransfertCaisseDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
//        TransfertCaisseDTO result = transfertCaisseService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }
    @PutMapping("transfert_caisse/update")
    public ResponseEntity<TransfertCaisseDTO> updateTransfertCaisse(@Valid @RequestBody TransfertCaisseDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        TransfertCaisseDTO result = transfertCaisseService.updateNewWithFlush(dTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("transfert_caisse/approuver")
    public ResponseEntity<TransfertCaisseDTO> approuveDemandeAchat(@Valid @RequestBody TransfertCaisseDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
        TransfertCaisseDTO result = transfertCaisseService.approuveAC(dto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("transfert_caisse/cancel_approuver")
    public ResponseEntity<TransfertCaisseDTO> Cancel_approuveDemandeAchat(@Valid @RequestBody TransfertCaisseDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
        TransfertCaisseDTO result = transfertCaisseService.CancelapprouveAC(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("transfert_caisse/delete/{code}")
    public ResponseEntity<TransfertCaisse> deleteTransfertCaisse(@PathVariable("Code") Integer code) {
        transfertCaisseService.deleteTransfertCaisse(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("transfert_caisse/edition/{code}")
//    public ResponseEntity<byte[]> getReport(@PathVariable Integer code) throws Exception {
// 
//
//        String fileNameJrxml = "src/main/resources/Reports/AlimentCaisse.jrxml";
//        paramDTO dTOs = paramService.findParamByCodeParamS("NomSociete");
//        TransfertCaisseDTO rslt = transfertCaisseService.findOne(code);
//
//        AccessUserDTO getsignature = accessUserService.findOneByCode(rslt.getCodeUserApprouver());
//
//        SocDTO societeDTO = societeService.findOne(1);
//        JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        Map<String, Object> params = new HashMap<>(); 
//        params.put("UserCreate", auth.getName());
//        params.put("CodeSaisie", rslt.getCodeSaisie());
//        params.put("societe", dTOs.getValeur());
//        params.put("devise", rslt.getDeviseDTO().getDesignationAr()); 
//        params.put("caisse", rslt.getCaisseDTO().getDesignationAr());
//        params.put("modeReglement", rslt.getModeReglementDTO().getDesignationAr());
//        params.put("montant", rslt.getMontant());
//        params.put("montantEnDevise", rslt.getMontantEnDevise());
//        params.put("tauxChange", rslt.getTauxChange());
//        params.put("Observation", rslt.getObservation());
//        params.put("dateCreate", rslt.getDateCreate());
//        params.put("designationEtatApprouve", rslt.getEtatApprouverDTO().getDesignation());
//
//        params.put("logo", societeDTO.getLogo());
//        params.put("signature", getsignature.getSignature());
//
//        JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
//        JRPdfExporter exporter = new JRPdfExporter();
//        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
//        exporter.setExporterInput(new SimpleExporterInput(print));
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
//        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
//        reportConfig.setSizePageToContent(true);
//        reportConfig.setForceLineBreakPolicy(false);
//        exporter.exportReport();
//        var res = pdfOutputStream.toByteArray();
//        var headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename= filename.pdf");
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(res);
//    }
}
