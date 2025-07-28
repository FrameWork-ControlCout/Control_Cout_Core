/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

//import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.OrderAchat;
import com.FrameWork.ControlCout.Achat.Edition.BonReceptionEdition;
import com.FrameWork.ControlCout.Achat.Edition.OrderAchatEdition;
import com.FrameWork.ControlCout.Achat.dto.OrderAchatDTO;
import com.FrameWork.ControlCout.Achat.factory.BonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.OrderAchatFactory;
import com.FrameWork.ControlCout.Achat.service.OrderAchatService;
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
public class OrderAchatRessource {

    private final OrderAchatService orderAchatService;
   private final SocService socService;

    public OrderAchatRessource(OrderAchatService orderAchatService, SocService socService) {
        this.orderAchatService = orderAchatService;
        this.socService = socService;
    }
    
   

    @GetMapping("order_achat/{code}")
    public ResponseEntity<OrderAchatDTO> getOrderAchatByCode(@PathVariable Integer code) {
        OrderAchatDTO dTO = orderAchatService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("order_achat/all")
    public ResponseEntity<List<OrderAchatDTO>> getAllOrderAchat() {
        return ResponseEntity.ok().body(orderAchatService.findAllOrderAchat());
    }

    @GetMapping("order_achat/findByEtatFacture")
    public ResponseEntity<List<OrderAchatDTO>> getAllOrderAchatByCodeEtatFacture(@RequestParam Integer codeEtatFacture) {
        return ResponseEntity.ok().body(orderAchatService.findOrderAchatByEtatFacture(codeEtatFacture));
    }
    
    
     @GetMapping("order_achat/findByFournisseur")
    public ResponseEntity<List<OrderAchatDTO>> getAllOrderAchatByFournisseur( @RequestParam Integer codeFournisseur) {
        return ResponseEntity.ok().body(orderAchatService.findOrderAchatByFournisseur(codeFournisseur));
    }
     @GetMapping("order_achat/findByFournisseurAndEtatFacture")
    public ResponseEntity<List<OrderAchatDTO>> getAllOrderAchatByFournisseurAndEtatFacture(@RequestParam Integer codeFournisseur,@RequestParam Integer codeEtatFacture) {
        return ResponseEntity.ok().body(orderAchatService.findOrderAchatByFournisseurAndCodeEtatFacture(codeFournisseur,codeEtatFacture));
    }

       @GetMapping("order_achat/findByDate")
    public ResponseEntity<Collection<OrderAchatDTO>> getAllOrderAchatByDateCreate(@RequestParam LocalDate dateDebut, @RequestParam LocalDate dateFin)  {
        return ResponseEntity.ok().body(orderAchatService.findFactureByDateCreateBetween(dateDebut,dateFin));
    }

    @PostMapping("order_achat")
    public ResponseEntity<OrderAchatDTO> postOrderAchat(@Valid @RequestBody OrderAchatDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        OrderAchatDTO result = orderAchatService.save(dTO);
        return ResponseEntity.created(new URI("/api/achat/" + result.getCode())).body(result);
    }

    @PutMapping("order_achat/update")
    public ResponseEntity<OrderAchatDTO> updateOrderAchat(@Valid @RequestBody OrderAchatDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        OrderAchatDTO result = orderAchatService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("order_achat/delete/{Code}")
    public ResponseEntity<OrderAchat> deleteOrderAchat(@PathVariable("Code") Integer code) {
        orderAchatService.deleteOrderAchat(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

       @GetMapping("order_achat/edition")
    public ResponseEntity<byte[]> editionOrderAchatWithDetails(@RequestParam(name = "code") Integer code) throws ReportSDKException, IOException, SQLException {
        List<OrderAchat> orderAchats = orderAchatService.findOneByCodeEdition(code);
       Collection<OrderAchatEdition> reportData = OrderAchatFactory.flattenOrderAchatForEdition(orderAchats);
        SocDTO socDTO = socService.findOne(1);
        Blob blob = new SerialBlob(socDTO.getLogo());
        SocEditionDTO socEditionDTO = new SocEditionDTO(socDTO.getCode(), blob, socDTO.getNomSociete(), socDTO.getNomSocieteAr());
        ReportClientDocument reportClientDoc = new ReportClientDocument();
        reportClientDoc.open("Reports/OrderAchat.rpt", 0); 
        reportClientDoc.getDatabaseController().setDataSource(reportData, OrderAchatEdition.class, "Commande", "Commande"); 
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
