package com.asu_tp.controllers;

import com.asu_tp.models.Directional;


import com.asu_tp.repo.DirectionalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PageController {


    @Autowired
    private DirectionalRepository directionalRepository;

    @GetMapping("/monitor")
    public String monitor( Model model) {
        ArrayList<String> well_id_list = new ArrayList<>();
        Optional<Directional> direct = directionalRepository.findFirstByOrderByIdDesc();
        if (!direct.isEmpty()){
            well_id_list.add(direct.orElse(null).getWellId());
        }
        model.addAttribute("well_id_list", well_id_list);
        String nodata = "нет данных";
        model.addAttribute("nodata", nodata);

        String plot_data ="[ { x: 0, y: 1 },{ x: 1, y: 1 } ]";

        ArrayList<Directional> datas = (ArrayList<Directional>) directionalRepository.findAll();

        StringBuilder str = new StringBuilder("[ ");

        ArrayList<Directional> last_data= new ArrayList<>();

        if (datas.size()>0) {
            for (int x = datas.size() - 2; x >0; x--) {
                System.out.println(datas.get(x).getRecordId());
                System.out.println(datas.get(x+1).getRecordId());
            if (datas.get(x+1).getRecordId() < datas.get(x).getRecordId()){
                break;
            }
            last_data.add(datas.get(x+1));

            }


            for (int x = last_data.size() - 1; x >0; x--) {
                str.append("{ x: ");
                str.append(last_data.get(x).getMesuarmentTime());
                str.append(", y: ");
                str.append(last_data.get(x).getDepthSvy());
                str.append(" },");
            }
        }

        str.append(" ]");
        plot_data = String.valueOf(str);
        System.out.println(plot_data);
        model.addAttribute("plot_data", plot_data);

        return "bur";
    }

    @GetMapping("/tp")
    public String texp( Model model) {

        return "tp";
    }

    @GetMapping("/wits")
    public String wits( Model model) {
        Iterable<Directional> datas = directionalRepository.findAll();
        model.addAttribute("data", datas);
        return "wits";
    }

}
