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

public class Sidetrack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int name;//"07020\n" + //Боковой ствол

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Sequence> sequences = new ArrayList<>();

    public Sidetrack(DirectionalDTO dto) {
        name = dto.getSidetrack();
        addSequenceData(dto);
    }


    public void addSequenceData(DirectionalDTO dto) {
        boolean isExist=false;

        if (sequences.size() <0) {
            sequences.add(new Sequence(dto));
        }else {
            for (Sequence sequence: sequences) {
                if (sequence.getName() == dto.getSequenceId()){
                    sequence.addRecordData(dto);
                    isExist=true;
                    break;
                }
            }
            if (!isExist) {
                sequences.add(new Sequence(dto));
                // System.out.println("added new Parameter: "+name);
            }
        }
    }
}
