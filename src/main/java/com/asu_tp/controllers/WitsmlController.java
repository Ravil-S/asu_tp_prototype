package com.asu_tp.controllers;

import com.asu_tp.models.WitsmlData;
import com.asu_tp.repo.WitsmlDataRepository;
import com.hashmapinc.tempus.WitsmlObjects.Util.WitsmlMarshal;
import com.hashmapinc.tempus.WitsmlObjects.v1411.ObjWells;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@PreAuthorize("hasAnyAuthority('ADMIN')")
@Controller
public class WitsmlController {

    @Autowired
    private WitsmlDataRepository witsmlDataRepository;

    @GetMapping("/witsml/export")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {

            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=w ell_no_xsl_1411_" + currentDateTime + ".xml";
            response.setHeader(headerKey, headerValue);

            Optional<WitsmlData> witsml = witsmlDataRepository.findFirstByOrderByIdDesc();

            String wellXmlOut = "ERROR! No witsml data";

            if (!witsml.isEmpty()) {
               /* try {
                    //serialize back to a witsml string
                    wellXmlOut = WitsmlMarshal.serialize(witsml.orElse(null));
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                WitsmlData data =  witsml.orElse(null);
                wellXmlOut = data.getWellsList().get(data.getWellsList().size()-1);
            }

            try {
            OutputStream outputStream = response.getOutputStream();
                outputStream.write(wellXmlOut.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/witsml/import")
    public String paramAdd( Model model) {
        model.addAttribute("spravka_page", "param-read-xls");
        return "witsml/read-file";
    }


    @PostMapping("/witsml/import")
    public String paramAddInput(@RequestParam("datafile") MultipartFile file, Model model) {

        String wellXmlIn="";
        if (!file.isEmpty()) {
            try {
                //read in the WITSML file
                wellXmlIn = new String(file.getBytes());
                //deserialize to an object

               // ObjWells wellsIn = WitsmlMarshal.deserialize(wellXmlIn, ObjWells.class);
                //serialize back to a witsml string
               // String wellXmlOut = WitsmlMarshal.serialize(wellsIn);

                Optional<WitsmlData> witsml = witsmlDataRepository.findFirstByOrderByIdDesc();

                if (!witsml.isEmpty()) {
                    witsml.orElse(null).addWellObj(wellXmlIn);
                    witsmlDataRepository.save(witsml.orElse(null));
                }else{
                    WitsmlData wd=new WitsmlData();
                    wd.addWellObj(wellXmlIn);
                    witsmlDataRepository.save(wd);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/import";
    }



}
