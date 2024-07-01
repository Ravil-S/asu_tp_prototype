package com.asu_tp.models;

import com.asu_tp.DTO.DirectionalDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

  //  private String wellId;// "0701PLOSCHAD\n" + //Идентификатор скважины
  //  private int sidetrack;//"07020\n" + //Боковой ствол
      private int name;// "07032\n" + //Идентификатор записи
 //   private int sequenceId;//"07040\n" + //Идентификатор последовательности записей

    private Date mesuarmentDate;//"07050\n" + //Дата
   // private Date mesuarmentTime;//"07060\n" + //Время

    private int stage;  ////"07070\n"код деятельности

    private double depthSvy =0;//"07083408" Глубина по показанию датчика – измеренная
    private double svyNorthSouthPosition =0;// "07180" Положение по оси Север-Юг (m)
    private double svyEastWestPosition =0;//"07190" Положение по оси Восток-Запад (m)

    public Record(DirectionalDTO dto) {
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");

        name = dto.getRecordId();
        try {
            mesuarmentDate = format.parse(dto.getMesuarmentDate()+dto.getMesuarmentTime());
        } catch (ParseException e) {
            e.printStackTrace();
            mesuarmentDate=new Date();
        }

        depthSvy = dto.getDepthSvy();
        svyEastWestPosition = dto.getSvyEastWestPosition();
        svyNorthSouthPosition = dto.getSvyNorthSouthPosition();
        stage=dto.getStage();
    }

    public void replace(DirectionalDTO dto){
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");

        name = dto.getRecordId();
        try {
            mesuarmentDate = format.parse(dto.getMesuarmentDate()+dto.getMesuarmentTime());
        } catch (ParseException e) {
            e.printStackTrace();
            mesuarmentDate=new Date();
        }

        depthSvy = dto.getDepthSvy();
        svyEastWestPosition = dto.getSvyEastWestPosition();
        svyNorthSouthPosition = dto.getSvyNorthSouthPosition();
        stage=dto.getStage();
    }

    public int getStageNumber() {
        int num=1;
        if (stage > 100) {
            num = stage-99;
        } else if (stage>1) {
            num = stage+5;
        }
        System.out.println("stage "+stage);
        System.out.println("num "+num);
        return num;
    }
}
