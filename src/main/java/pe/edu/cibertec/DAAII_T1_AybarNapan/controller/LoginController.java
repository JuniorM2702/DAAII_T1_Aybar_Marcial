package pe.edu.cibertec.DAAII_T1_AybarNapan.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.cibertec.DAAII_T1_AybarNapan.model.bd.Usuario;
import pe.edu.cibertec.DAAII_T1_AybarNapan.model.dto.UsuarioDto;
import pe.edu.cibertec.DAAII_T1_AybarNapan.service.UsuarioService;

@AllArgsConstructor
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
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNomusuario(username);
        model.addAttribute("usuario", usuarioDto);
        return "redirect:/auth/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model){
        String username = authentication.getName();
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNomusuario(username);
        model.addAttribute("usuario", usuarioDto);
        return "auth/home";
    }

    @GetMapping("/cambiar-password")
    public String mostrarCambiarPassword(Model model) {
        return "auth/frmpassword";
    }

    @PostMapping("/cambiar-password")
    public String cambiarPassword(@RequestParam("newPassword") String newPassword,
                                  Authentication authentication,
                                  RedirectAttributes redirectAttributes) {
        String username = authentication.getName();
        try {
            usuarioService.CambiarPassword(username, newPassword);
            redirectAttributes.addFlashAttribute("success", "Contraseña cambiada exitosamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/auth/cambiar-password";
    }



}
