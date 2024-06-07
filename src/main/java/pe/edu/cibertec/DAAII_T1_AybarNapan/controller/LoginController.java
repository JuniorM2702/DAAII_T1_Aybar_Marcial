package pe.edu.cibertec.DAAII_T1_AybarNapan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.DAAII_T1_AybarNapan.model.bd.Usuario;
import pe.edu.cibertec.DAAII_T1_AybarNapan.service.UsuarioService;

@Controller
@RequestMapping("/auth")
public class LoginController {
    private UsuarioService usuarioService;
    @GetMapping("/login")
    public String login(){
        return "auth/frmlogin";
    }

    @GetMapping("/register")
    public String registro(){
        return "/auth/frmregister";
    }

    @PostMapping("/save-user")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "/auth/frmlogin";
    }

    @GetMapping("/login-success")
    public String loginSuccess(Authentication authentication, Model model){
        String username = authentication.getName();
        model.addAttribute("username", username);

        return "redirect:/auth/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "auth/home";
    }
}
