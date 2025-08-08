/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.web;
 
import com.FrameWork.ControlCout.Parametrage.service.ParamService;
import com.FrameWork.ControlCout.Parametrage.service.SocService;
import com.FrameWork.ControlCout.Tresorerie.domaine.AlimentationCaisse;
import com.FrameWork.ControlCout.Tresorerie.dto.AlimentationCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.service.AlimentationCaisseService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; 

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/recette/")
public class AlimentationCaisseRessource {

    private final AlimentationCaisseService alimentationCaisseService;
    private final ParamService paramService;
    private final SocService societeService;

//    private final AccessUserService accessUserService;

    public AlimentationCaisseRessource(AlimentationCaisseService alimentationCaisseService, ParamService paramService, SocService societeService) {
        this.alimentationCaisseService = alimentationCaisseService;
        this.paramService = paramService;
        this.societeService = societeService;
    }

   

    @GetMapping("alimentation_caisse/{code}")
    public ResponseEntity<AlimentationCaisseDTO> getAlimentationCaisseByCode(@PathVariable Integer code) {
        AlimentationCaisseDTO dTO = alimentationCaisseService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("alimentation_caisse/all")
    public ResponseEntity<List<AlimentationCaisseDTO>> getAllAlimentationCaisse() {
        return ResponseEntity.ok().body(alimentationCaisseService.findAllAlimentationCaisse());
    }

    @GetMapping("alimentation_caisse/codeCaisse")
    public ResponseEntity<Collection<AlimentationCaisseDTO>> getAlimentationCaisseByCodeCaisse(@RequestParam Collection<Integer> codeCaisse) {
        Collection<AlimentationCaisseDTO> dTOs = alimentationCaisseService.findByCodeCaisse(codeCaisse);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("alimentation_caisse/codeDevise")
    public ResponseEntity<Collection<AlimentationCaisseDTO>> getAlimentationCaisseByCodeDevise(@RequestParam Collection<Integer> codeDevise) {
        Collection<AlimentationCaisseDTO> dTOs = alimentationCaisseService.findByCodeDevise(codeDevise);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("alimentation_caisse/EtatApprouver/{codeEtatApprouver}")
    public ResponseEntity<List<AlimentationCaisseDTO>> getAppelOffreByCodeEtatApprouve(@PathVariable Integer codeEtatApprouver) {
        List<AlimentationCaisseDTO> dto = alimentationCaisseService.findByEtatApprouver(codeEtatApprouver);
        return ResponseEntity.ok().body(dto);

    }

    @PostMapping("alimentation_caisse")
    public ResponseEntity<AlimentationCaisseDTO> postAlimentationCaisse(@Valid @RequestBody AlimentationCaisseDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        AlimentationCaisseDTO result = alimentationCaisseService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

//    @PutMapping("alimentation_caisse/update")
//    public ResponseEntity<AlimentationCaisseDTO> updateAlimentationCaisse(@Valid @RequestBody AlimentationCaisseDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
//        AlimentationCaisseDTO result = alimentationCaisseService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }
    @PutMapping("alimentation_caisse/update")
    public ResponseEntity<AlimentationCaisseDTO> updateAlimentationCaisse(@Valid @RequestBody AlimentationCaisseDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        AlimentationCaisseDTO result = alimentationCaisseService.updateNewWithFlush(dTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("alimentation_caisse/approuver")
    public ResponseEntity<AlimentationCaisseDTO> approuveDemandeAchat(@Valid @RequestBody AlimentationCaisseDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
        AlimentationCaisseDTO result = alimentationCaisseService.approuveAC(dto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("alimentation_caisse/cancel_approuver")
    public ResponseEntity<AlimentationCaisseDTO> Cancel_approuveDemandeAchat(@Valid @RequestBody AlimentationCaisseDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
        AlimentationCaisseDTO result = alimentationCaisseService.CancelapprouveAC(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("alimentation_caisse/delete/{code}")
    public ResponseEntity<AlimentationCaisse> deleteAlimentationCaisse(@PathVariable("Code") Integer code) {
        alimentationCaisseService.deleteAlimentationCaisse(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("alimentation_caisse/edition/{code}")
//    public ResponseEntity<byte[]> getReport(@PathVariable Integer code) throws Exception {
//
//        Collection<DetailsAlimentationCaisseDTO> dto = alimentationCaisseService.findOneWithDetails(code);
//
//        String fileNameJrxml = "src/main/resources/Reports/AlimentCaisse.jrxml";
//        paramDTO dTOs = paramService.findParamByCodeParamS("NomSociete");
//        AlimentationCaisseDTO rslt = alimentationCaisseService.findOne(code);
//
//        Preconditions.checkArgument(rslt.getCodeUserApprouver() != null, "error.User.Approuve.Found");
//        AccessUserDTO getsignature = accessUserService.findOneByCode(rslt.getCodeUserApprouver());
//
//        SocDTO societeDTO = societeService.findOne(1);
//        JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("ItemDataSource", new JRBeanCollectionDataSource(dto));
//        params.put("UserCreate", auth.getName());
//        params.put("CodeSaisie", rslt.getCodeSaisie());
//        params.put("societe", dTOs.getValeur());
//        params.put("devise", rslt.getDeviseDTO().getDesignationAr());
//        params.put("typeRecette", rslt.getDetailsAlimentationCaisseDTOs().iterator().next().getDesignationArTypeRecette());
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
//
//    @GetMapping("alimentation_caisse/excel/{code}")
//    public ResponseEntity<byte[]> getReportExcel(@PathVariable Integer code) throws Exception {
//
//        Collection<DetailsAlimentationCaisseDTO> dto = alimentationCaisseService.findOneWithDetails(code);
//
//        String fileNameJrxml = "src/main/resources/Reports/AlimentCaisse.jrxml";
//        paramDTO dTOs = paramService.findParamByCodeParamS("NomSociete");
//        AlimentationCaisseDTO rslt = alimentationCaisseService.findOne(code);
//
//        Preconditions.checkArgument(rslt.getCodeUserApprouver() != null, "error.User.Approuve.Found");
//        AccessUserDTO getsignature = accessUserService.findOneByCode(rslt.getCodeUserApprouver());
//
//        SocDTO societeDTO = societeService.findOne(1);
//        JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("ItemDataSource", new JRBeanCollectionDataSource(dto));
//        params.put("UserCreate", auth.getName());
//        params.put("CodeSaisie", rslt.getCodeSaisie());
//        params.put("societe", dTOs.getValeur());
//        params.put("devise", rslt.getDeviseDTO().getDesignationAr());
//        params.put("typeRecette", rslt.getDetailsAlimentationCaisseDTOs().iterator().next().getDesignationArTypeRecette());
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
//        final Map<String, Object> parameters = new HashMap<>();
//        parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
//
//        JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
//        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
//        configuration.setOnePagePerSheet(true);
//        configuration.setDetectCellType(true); // Detect cell types (date and etc.)
//        configuration.setWhitePageBackground(false); // No white background!
// 
//
//        // No spaces between rows and columns 
//        configuration.setRemoveEmptySpaceBetweenRows(true);
//        configuration.setRemoveEmptySpaceBetweenColumns(true);
//        configuration.setFontSizeFixEnabled(true);
//
//        JRXlsxExporter exporter = new JRXlsxExporter();
//        exporter.setConfiguration(configuration);
//        ByteArrayOutputStream excelOutputStream = new ByteArrayOutputStream();
//        exporter.setExporterInput(new SimpleExporterInput(print));
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(excelOutputStream));
//
//        exporter.exportReport();
//
//        byte[] excelData = excelOutputStream.toByteArray();
//        HttpHeaders headers = new HttpHeaders();
//        MediaType excelMediaType = new MediaType("application", "vnd.ms-excel");
//        headers.add("Content-Disposition", "attachment; filename=report.xls");
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(excelMediaType)
//                .body(excelData);
//    }

}
