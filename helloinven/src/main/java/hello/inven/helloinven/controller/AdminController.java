package hello.inven.helloinven.controller;


import hello.inven.helloinven.model.MyUser;
import hello.inven.helloinven.model.Role;
import hello.inven.helloinven.repository.RoleRepository;
import hello.inven.helloinven.service.AdminService;
import hello.inven.helloinven.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @GetMapping(value = "/admin/admin")
    public String admin() {
        return "/admin/admin";
    }

    @GetMapping(value = "/admin/employeelist")
    public String employeeList(Model model){
        List<MyUser> employees = adminService.findAll();
        for (MyUser emp: employees) {
            System.out.print(emp.getEmail());
            System.out.print(emp.getName());
            System.out.print("ROLE: " + emp.getRole().getRole());
        }
        model.addAttribute("employees", employees);
        return "admin/employeelist";
    }

    @GetMapping(value = "/admin/register")
    public String registerEmployeeForm(Model model){
        model.addAttribute("newUser", new MyUser());
        return "admin/register";
    }

    @Autowired
    RoleRepository roleRepository;

//    https://medium.com/@grokwich/spring-boot-thymeleaf-html-form-handling-762ef0d51327
    @ModelAttribute("roleList")
    public String[] getRoleList() {
        List<Role> roleAll = roleRepository.findAll();
        String[] roleName = new String[5];
        int i = 0;
        for (Role role: roleAll) {
            String roleTemp = role.getRole();
            roleName[i++] = roleTemp;
        }
        return roleName;

    }
//    https://memorynotfound.com/spring-security-user-registration-example-thymeleaf/
    @PostMapping(value = "/admin/register")
    public String registerEmployee(@ModelAttribute("newUser") @Valid MyUser newUser,
                                   BindingResult bindingResult, Model model) {
        MyUser existingUser = adminService.findByUsername(newUser.getUsername());
        System.out.print("Sudah nemu existingUser: " + existingUser);
        System.out.print("newUser: " + newUser.getName() + "username: " + newUser.getUsername());
//        model["roleList"] = getRoleList();
        if (existingUser != null) {
            bindingResult.rejectValue("username", null, "Username is already exists");

        }
//        if (bindingResult.hasErrors()){
//            return "admin/register";
//        }
//        else {
//            System.out.print("Everything is OKAY to Register");
//        }
        myUserDetailsService.save(newUser);
        return "redirect:/admin/register?success";

    }
}
