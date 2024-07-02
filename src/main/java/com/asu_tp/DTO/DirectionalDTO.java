package com.asu_tp.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DirectionalDTO {

    private String wellId;// "0701PLOSCHAD\n" + //Идентификатор скважины
    private int sidetrack;//"07020\n" + //Боковой ствол
    private int recordId;// "07032\n" + //Идентификатор записи
    private int sequenceId;//"07040\n" + //Идентификатор последовательности записей

    private String mesuarmentDate;//"07050\n" + //Дата
    private String mesuarmentTime;//"07060\n" + //Время

    private int stage;  ////"07070\n"код деятельности

    private double depthSvy =0;//"07083408" Глубина по показанию датчика – измеренная
    private double svyNorthSouthPosition =0;// "07180" Положение по оси Север-Юг (m)
    private double svyEastWestPosition =0;//"07190" Положение по оси Восток-Запад (m)


}
