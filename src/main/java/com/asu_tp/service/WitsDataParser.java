package com.asu_tp.service;

import com.asu_tp.models.Directional;
import com.asu_tp.repo.DirectionalRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class WitsDataParser {

   // private ArrayList<DirectionalDTO> directionalDTOS = new ArrayList<>();
    private ArrayList<Directional> directionalList = new ArrayList<>();

    public WitsDataParser() {
    }


    public void parse(String data){

        Directional dto = null;

        String[] words = data.split("\n");

        for (String word : words) {
            // int index1 = indexOfRegEx(word, "(\\d+)");
            word = word.replace ("\r", "");

            if (word.contains("&&") && dto==null) {
                dto = new Directional();
                continue;
            }

           // int index1 = word.indexOf(";");
            if (word.length()>4 && dto!=null) {
                String value = word.substring(4);
                String record = word.substring(0, 4);

                if (record.contains("0701")) {
                    dto.setWellId(value);
                    continue;
                }
                if (record.contains("0702")) {
                    dto.setSidetrack(Integer.parseInt(value));
                    continue;
                }
                if (record.contains("0703")) {
                    dto.setRecordId(Integer.parseInt(value));
                    continue;
                }
                if (record.contains("0704")) {
                    dto.setSequenceId(Integer.parseInt(value));
                    continue;
                }
                if (record.contains("0705")) {
                    dto.setMesuarmentDate(value);
                    continue;
                }
                if (record.contains("0706")) {
                    dto.setMesuarmentTime(value);
                    continue;
                }
                if (record.contains("0708")) {
                    dto.setDepthSvy(Double.parseDouble(value));
                    continue;
                }
                if (record.contains("0718")) {
                    dto.setSvyNorthSouthPosition(Double.parseDouble(value));
                    continue;
                }
                if (record.contains("0719")) {
                    dto.setSvyEastWestPosition(Double.parseDouble(value));
                    continue;
                }
            }

            if (word.contains("!!") && dto!=null) {
                directionalList.add(dto);
                dto=null;
                continue;
            }

        }
/*
        private String 	wellId;// "0701PLOSCHAD\n" + //Идентификатор скважины
        private int sidetrack;//"07020\n" + //Боковой ствол
        private int recordId;// "07032\n" + //Идентификатор записи
        private int sequenceId;//"07040\n" + //Идентификатор последовательности записей

        private String mesuarmentDate;//"07050\n" + //Дата
        private String mesuarmentTime;//"07060\n" + //Время

        private double depthSvy=0;//"07083408" Глубина по показанию датчика – измеренная
        private double svyNorthSouthPosition=0;// "07180" Положение по оси Север-Юг (m)
        private double  svyEastWestPosition=0;//"07190" Положение по оси Восток-Запад (m)
        */
    }

    public void writeToDB(DirectionalRepository directionalRepository){
        if (directionalList.size()>10){
            for (Directional dto:directionalList) {
                if (dto!=null) { directionalRepository.save(dto);}
            }
            directionalList.clear();
        }

    }
}
