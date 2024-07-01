package com.asu_tp.controllers;

import com.asu_tp.models.Record;


import com.asu_tp.models.Sequence;
import com.asu_tp.models.Sidetrack;
import com.asu_tp.models.Well;
import com.asu_tp.repo.RecordRepository;

import com.asu_tp.repo.WellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PageController {


    @Autowired
    private WellRepository wellRepository;



    @GetMapping("/monitor")
    public String monitor( Model model) {
        int stage=1;

        ArrayList<String> well_id_list = new ArrayList<>();

        Optional<Well> well = wellRepository.findFirstByOrderByIdDesc();

        String plot_data ="[ { x: 0, y: 1 },{ x: 1, y: 1 } ]";
        String plot_data11 ="[ { x: 0, y: 1 },{ x: 1, y: 1 } ]";
        String plot_data2 ="[ { x: 0, y: 1 },{ x: 1, y: 1 } ]";
        String plot_data21 ="[ { x: 0, y: 1 },{ x: 1, y: 1 } ]";
        String plot_data3 ="[ { x: 0, y: 1 },{ x: 1, y: 1 } ]";
        String plot_data31 ="[ { x: 0, y: 1 },{ x: 1, y: 1 } ]";

        if (!well.isEmpty()){
            well_id_list.add(well.orElse(null).getName());

            List<Sidetrack> sidetrackList =  well.orElse(null).getSidetracks();
            if (sidetrackList.size()>0){
                List<Sequence> sequenceList = sidetrackList.get(sidetrackList.size()-1).getSequences();
                if (sequenceList.size()>0){
                    List<Record> recordList = sequenceList.get(sequenceList.size()-1).getRecords();

                    recordList.sort((h1, h2) -> h1.getMesuarmentDate().compareTo(h2.getMesuarmentDate()));
                    //передача данных о глубине
                    StringBuilder str = new StringBuilder("[ ");
                    for (Record record : recordList  ) {
                        str.append("{ x: ");
                        Long time = ((record.getMesuarmentDate().getTime()-
                                recordList.get(0).getMesuarmentDate().getTime())/1000);
                        str.append(time);
                        str.append(", y: ");
                        str.append(record.getDepthSvy());
                        str.append(" },");
                    }
                    str.append(" ]");
                    plot_data = String.valueOf(str);
                    //генерация планируемых данных о глубине
                    Random rnd1 = new Random(145235);
                    str = new StringBuilder("[ ");
                    for (int i=0; i< recordList.size(); i++) {
                        str.append("{ x: ");
                        Long time = ((recordList.get(i).getMesuarmentDate().getTime()-
                                recordList.get(0).getMesuarmentDate().getTime())/1000);
                        str.append(time);
                        str.append(", y: ");
                        double deltaDepth = 0 ;
                        if (i>0) deltaDepth = recordList.get(i).getDepthSvy()-recordList.get(i-1).getDepthSvy();
                        str.append(recordList.get(i).getDepthSvy()+((rnd1.nextDouble()-0.5) *0.2*deltaDepth));
                        str.append(" },");
                    }
                    str.append(" ]");
                    plot_data11 = String.valueOf(str);

                    //передача данных о отклонении
                    str = new StringBuilder("[ ");
                    for (Record record : recordList  ) {
                        str.append("{ x: ");
                        Long time = ((record.getMesuarmentDate().getTime()-
                                recordList.get(0).getMesuarmentDate().getTime())/1000);
                        str.append(time);
                        str.append(", y: ");
                        str.append(record.getSvyEastWestPosition());
                        str.append(" },");
                    }
                    str.append(" ]");
                    plot_data2 = String.valueOf(str);

                    //генерация планируемых данных о отклонении
                    Random rnd2 = new Random(457834);
                    str = new StringBuilder("[ ");
                    for (int i=0; i< recordList.size(); i++) {
                        str.append("{ x: ");
                        Long time = ((recordList.get(i).getMesuarmentDate().getTime()-
                                recordList.get(0).getMesuarmentDate().getTime())/1000);
                        str.append(time);
                        str.append(", y: ");
                        double deltaDepth = 0 ;
                        if (i>0) deltaDepth = recordList.get(i).getSvyEastWestPosition()-recordList.get(i-1).getSvyEastWestPosition();
                        str.append(recordList.get(i).getSvyEastWestPosition()+((rnd2.nextDouble()-0.5) *0.2*deltaDepth));
                        str.append(" },");
                    }
                    str.append(" ]");
                    plot_data21 = String.valueOf(str);

                    //передача данных о отклонении
                    str = new StringBuilder("[ ");
                    for (Record record : recordList  ) {
                        str.append("{ x: ");
                        Long time = ((record.getMesuarmentDate().getTime()-
                                recordList.get(0).getMesuarmentDate().getTime())/1000);
                        str.append(time);
                        str.append(", y: ");
                        str.append(record.getSvyNorthSouthPosition());
                        str.append(" },");
                    }
                    str.append(" ]");
                    plot_data3 = String.valueOf(str);

                    //генерация планируемых данных о отклонении
                    Random rnd3 = new Random(655686);
                    str = new StringBuilder("[ ");
                    for (int i=0; i< recordList.size(); i++) {
                        str.append("{ x: ");
                        Long time = ((recordList.get(i).getMesuarmentDate().getTime()-
                                recordList.get(0).getMesuarmentDate().getTime())/1000);
                        str.append(time);
                        str.append(", y: ");
                        double deltaDepth = 0 ;
                        if (i>0) deltaDepth = recordList.get(i).getSvyNorthSouthPosition()-recordList.get(i-1).getSvyNorthSouthPosition();
                        str.append(recordList.get(i).getSvyNorthSouthPosition()+((rnd1.nextDouble()-0.5) *0.2*deltaDepth));
                        str.append(" },");
                    }
                    str.append(" ]");
                    plot_data31 = String.valueOf(str);

                    stage=recordList.get(recordList.size()-1).getStageNumber();
                }
            }
        }

        model.addAttribute("stage", stage);

        model.addAttribute("well_id_list", well_id_list);
        String nodata = "нет данных";
        model.addAttribute("nodata", nodata);

        model.addAttribute("plot_data", plot_data);
        model.addAttribute("plot_data11", plot_data11);
        model.addAttribute("plot_data2", plot_data2);
        model.addAttribute("plot_data21", plot_data21);
        model.addAttribute("plot_data31", plot_data31);
        model.addAttribute("plot_data3", plot_data3);

        return "bur";
    }

    @GetMapping("/tp")
    public String texp( Model model) {

        return "tp";
    }

    @GetMapping("/wits")
    public String wits( Model model) {

        List<Record> recordList= new ArrayList<>();

        ArrayList<String> well_id_list = new ArrayList<>();

        Optional<Well> well = wellRepository.findFirstByOrderByIdDesc();

        String plot_data ="[ { x: 0, y: 1 },{ x: 1, y: 1 } ]";

        if (!well.isEmpty()){
            well_id_list.add(well.orElse(null).getName());

            List<Sidetrack> sidetrackList =  well.orElse(null).getSidetracks();
            if (sidetrackList.size()>0){
                List<Sequence> sequenceList = sidetrackList.get(sidetrackList.size()-1).getSequences();
                if (sequenceList.size()>0){
                    recordList = sequenceList.get(sequenceList.size()-1).getRecords();

                }
            }
        }

        model.addAttribute("data", recordList);
        return "wits";
    }

}
