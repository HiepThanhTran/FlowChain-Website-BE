package com.fh.scm.controllers.admin;

import com.fh.scm.dto.MessageResponse;
import com.fh.scm.pojo.Warehouse;
import com.fh.scm.services.WarehouseService;
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
@RequestMapping(path = "/admin/warehouses", produces = "application/json; charset=UTF-8")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public String listWarehouse(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("warehouses", warehouseService.getAll(params));

        return "warehouses";
    }

    @RequestMapping(path = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String addWarehouse(HttpServletRequest request, Model model, @ModelAttribute(value = "warehouse") @Valid Warehouse warehouse,
                               BindingResult bindingResult) {
        if (request.getMethod().equals("POST")) {
            if (bindingResult.hasErrors()) {
                List<MessageResponse> errors = MessageResponse.fromBindingResult(bindingResult);
                model.addAttribute("errors", errors);

                return "add_warehouse";
            }

            warehouseService.insert(warehouse);

            return "redirect:/admin/warehouses";
        }

        return "add_warehouse";
    }

    @RequestMapping(path = "/edit/{warehouseId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String editWarehouse(HttpServletRequest request, Model model, @PathVariable(value = "warehouseId") Long id,
                                @ModelAttribute(value = "warehouse") @Valid Warehouse warehouse, BindingResult bindingResult) {
        if (request.getMethod().equals("POST")) {
            if (bindingResult.hasErrors()) {
                List<MessageResponse> errors = MessageResponse.fromBindingResult(bindingResult);
                model.addAttribute("errors", errors);

                return "edit_warehouse";
            }

            warehouseService.update(warehouse);

            return "redirect:/admin/warehouses";
        }

        model.addAttribute("warehouse", warehouseService.get(id));

        return "edit_warehouse";
    }

    @DeleteMapping(path = "/delete/{warehouseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteWarehouse(@PathVariable(value = "warehouseId") Long id) {
        warehouseService.delete(id);

        return "redirect:/admin/warehouses";
    }

    @DeleteMapping(path = "/hide/{warehouseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String hideWarehouse(@PathVariable(value = "warehouseId") Long id) {
        warehouseService.softDelete(id);

        return "redirect:/admin/warehouses";
    }
}