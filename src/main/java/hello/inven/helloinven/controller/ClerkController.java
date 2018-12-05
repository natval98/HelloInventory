package hello.inven.helloinven.controller;

import hello.inven.helloinven.model.MyUser;
import hello.inven.helloinven.model.ResponseAjax;
import hello.inven.helloinven.service.AdminService;
import hello.inven.helloinven.service.ClerkService;
import hello.inven.helloinven.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClerkController {

    @Autowired
    ClerkService clerkService;

    @GetMapping(value = "/clerk/employeelist") // hanya menampilkan employee yang termasuk Role Manager / Employee
    @ResponseBody
    public ResponseAjax managerAndEmployeeList(){
        return clerkService.findManagerAndEmployee();
    }

    @GetMapping(value = "/clerk/item/{id}/assign")
    public String itemAssign(@PathVariable Long id, Model model){
        return "clerk/item-serial-assign";
    }

    @PostMapping(value = "/clerk/item/{id}/assign")
    @ResponseBody
    public ResponseAjax itemAssignPost(@PathVariable Long id, @RequestParam(value = "serial_employee[]") List<Long> employeeValues){
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUser clerk = myUserDetails.getUser();

        return clerkService.assignItemSerial(clerk, id, employeeValues);
    }


}
