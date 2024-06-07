package pe.edu.cibertec.DAAII_T1_AybarNapan.service;

import pe.edu.cibertec.DAAII_T1_AybarNapan.model.bd.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario buscarUsuarioXNomUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);

    void CambiarPassword(String username, String newPassword);

    List<Usuario> listarUsuario();
    Usuario buscarUsuarioXIdUsuario(Integer idusuario);
}
