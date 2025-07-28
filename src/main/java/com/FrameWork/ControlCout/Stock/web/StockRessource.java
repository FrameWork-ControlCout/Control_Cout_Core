/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.web;
 
import com.FrameWork.ControlCout.Parametrage.dto.SocDTO;
import com.FrameWork.ControlCout.Parametrage.dto.SocEditionDTO;
import com.FrameWork.ControlCout.Parametrage.service.SocService; 
import com.FrameWork.ControlCout.Stock.Edition.SoldeDepotEdition;
import com.FrameWork.ControlCout.Stock.domaine.EtatStock;
import com.FrameWork.ControlCout.Stock.factory.EtatStockFactory;
import com.FrameWork.ControlCout.Stock.service.GestionStockService;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.crystaldecisions.sdk.occa.report.application.ParameterFieldController;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/stock")
public class StockRessource {

    private final GestionStockService gestionStockService;
   private final SocService socService;

    public StockRessource(GestionStockService gestionStockService, SocService socService) {
        this.gestionStockService = gestionStockService;
        this.socService = socService;
    }

     

    @GetMapping("/disponible")
    public ResponseEntity<BigDecimal> getStockDisponible(
            @RequestParam Integer codeArticle,
            @RequestParam Integer codeDepot) {
        
        BigDecimal qte = gestionStockService.getQuantiteDisponible(codeArticle, codeDepot);
        return ResponseEntity.ok(qte);
    }
    
//      @GetMapping("/solde_depot/edition")
//    public ResponseEntity<byte[]> editionSoldeDepot(@RequestParam(name = "code") Integer code) throws ReportSDKException, IOException, SQLException {
//        List<EtatStock> etatStocks = gestionStockService.getEtatStockForDepot(code);
//       Collection<SoldeDepotEdition> reportData = EtatStockFactory.flattenSoldeDepotForEdition(etatStocks);
//        SocDTO socDTO = socService.findOne(1);
//        Blob blob = new SerialBlob(socDTO.getLogo());
//        SocEditionDTO socEditionDTO = new SocEditionDTO(socDTO.getCode(), blob, socDTO.getNomSociete(), socDTO.getNomSocieteAr());
//        ReportClientDocument reportClientDoc = new ReportClientDocument();
//        reportClientDoc.open("Reports/SoldeDepot.rpt", 0); 
//        reportClientDoc.getDatabaseController().setDataSource(reportData, SoldeDepotEdition.class, "Commande", "Commande"); 
//        reportClientDoc.getSubreportController()
//                .getSubreport("entete.rpt")
//                .getDatabaseController()
//                .setDataSource(
//                        java.util.Arrays.asList(socEditionDTO),
//                        SocEditionDTO.class,
//                        "societe",
//                        "societe"
//                ); 
//        ParameterFieldController paramController = reportClientDoc.getDataDefController().getParameterFieldController();
//        String user = SecurityContextHolder.getContext().getAuthentication().getName();
//        paramController.setCurrentValue("", "user", user);
//        ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream) reportClientDoc.getPrintOutputController().export(ReportExportFormat.PDF);
//        reportClientDoc.close();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        return ResponseEntity.ok().headers(headers).body(Helper.read(byteArrayInputStream));
//    }
    
    
}
