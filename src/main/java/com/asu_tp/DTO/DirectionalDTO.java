package com.asu_tp.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectionalDTO {

    private String 	well_Id;// "0701PLOSCHAD\n" + //Идентификатор скважины
    private int sidetrack;//"07020\n" + //Боковой ствол
    private int record_Id;// "07032\n" + //Идентификатор записи
    private int sequence_Id;//"07040\n" + //Идентификатор последовательности записей

    private String mesuarment_date;//"07050\n" + //Дата
    private String mesuarment_time;//"07060\n" + //Время

    private double depth_Svy=0;//"07083408" Глубина по показанию датчика – измеренная
    private double svy_North_South_Position=0;// "07180" Положение по оси Север-Юг (m)
    private double  svy_East_West_Position=0;//"07190" Положение по оси Восток-Запад (m)

    public DirectionalDTO() {
    }


}
