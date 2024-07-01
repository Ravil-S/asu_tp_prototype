package com.asu_tp.models;

import com.hashmapinc.tempus.WitsmlObjects.Util.WitsmlMarshal;
import com.hashmapinc.tempus.WitsmlObjects.v1411.ObjWells;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WitsmlData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    List<String> wellsList  = new ArrayList<>();

    public void addWellObj(String objWells){
        wellsList.add(objWells);
    }

    public static ObjWells getObjWells(String wellXmlIn) {
        ObjWells wellsIn=null;
        try {
            //deserialize to an object
            wellsIn = WitsmlMarshal.deserialize(wellXmlIn, ObjWells.class);
            //serialize back to a witsml string

        } catch ( JAXBException e) {
            e.printStackTrace();
        }

        return wellsIn;
    }


}
