package com.asu_tp.models;

import com.asu_tp.DTO.DirectionalDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int name;//"07040\n" + //Идентификатор последовательности записей

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Record> records  = new ArrayList<>();

    public Sequence(DirectionalDTO dto) {
        name = dto.getSequenceId();
        addRecordData(dto);
    }

    public void addRecordData(DirectionalDTO dto) {
        boolean isExist=false;

        if (records.size() <0) {
            records.add(new Record(dto));
        }else {
            for (Record record: records) {
                if (record.getName() == dto.getRecordId()){
                    record.replace(dto);
                    isExist=true;
                    break;
                }
            }
            if (!isExist) {
                records.add(new Record(dto));
                // System.out.println("added new Parameter: "+name);
            }
        }
    }
}
