package com.fh.scms.controllers.admin;

import com.fh.scms.dto.MessageResponse;
import com.fh.scms.pojo.Unit;
import com.fh.scms.services.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin/units", produces = "application/json; charset=UTF-8")
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    public String listUnit(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("units", this.unitService.findAllWithFilter(params));

        return "units";
    }

    @GetMapping(path = "/add")
    public String addUnit(Model model) {
        model.addAttribute("unit", new Unit());

        return "add_unit";
    }

    @PostMapping(path = "/add")
    public String addUnit(Model model, @ModelAttribute(value = "unit") @Valid Unit unit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<MessageResponse> errors = MessageResponse.fromBindingResult(bindingResult);
            model.addAttribute("errors", errors);

            return "add_unit";
        }

        this.unitService.save(unit);

        return "redirect:/admin/units";
    }

    @PostMapping(path = "/edit/{unitId}")
    public String editUnit(Model model, @PathVariable(value = "unitId") Long id,
                           @ModelAttribute(value = "unit") @Valid Unit unit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<MessageResponse> errors = MessageResponse.fromBindingResult(bindingResult);
            model.addAttribute("errors", errors);

            return "edit_unit";
        }

        this.unitService.update(unit);

        return "redirect:/admin/units";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{unitId}")
    public String deleteUnit(@PathVariable(value = "unitId") Long id) {
        this.unitService.delete(id);

        return "redirect:/admin/units";
    }
}