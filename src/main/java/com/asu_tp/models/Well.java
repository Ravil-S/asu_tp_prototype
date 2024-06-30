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
public class Well {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;// "0701PLOSCHAD\n" + //Идентификатор скважины


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Sidetrack> sidetracks = new ArrayList<>();

    public Well(DirectionalDTO dto) {
        name = dto.getWellId();
        addSidetrackData(dto);
    }

    public void addSidetrackData(DirectionalDTO dto){
        boolean isExist=false;

        if (sidetracks.size() <0) {
            sidetracks.add(new Sidetrack(dto));
        }else {
            for (Sidetrack strack: sidetracks) {
                if (strack.getName() == dto.getSidetrack()){
                    strack.addSequenceData(dto);
                    isExist=true;
                    break;
                }
            }
            if (!isExist) {
                sidetracks.add(new Sidetrack(dto));
                // System.out.println("added new Parameter: "+name);
            }
        }
    }

}
