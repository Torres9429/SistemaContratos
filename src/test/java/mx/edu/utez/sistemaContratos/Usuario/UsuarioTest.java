package mx.edu.utez.sistemaContratos.Usuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    @Test
    void registrarUsuarioConCamposCompletos() {
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario("Alondra", "Medina", "alomedina@gmail.com", "7771234567", "PasswordAlo1", "Usuario", true);

        boolean resultado = usuarioService.registrarUsuario(usuario);

        assertTrue(resultado);
        assertEquals(1, usuarioService.consultarUsuarios().size());
    }

    @Test
    void registrarUsuarioConCamposInvalidos() {
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario("", "Medina", "correo-invalido@", "7771234567", "PasswordAlo1", "Usuario", true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> usuarioService.registrarUsuario(usuario));

        assertEquals("El correo es obligatorio", exception.getMessage());
    }

    @Test
    void registrarUsuarioConCorreoDuplicado() {
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario1 = new Usuario("Alondra", "Medina", "alomedina@gmail.com", "7771234567", "PasswordAlo1", "Usuario", true);
        Usuario usuario2 = new Usuario("Alondra", "Medina", "alomedina@gmail.com", "7771234567", "PasswordAlo1", "Usuario", true);

        usuarioService.registrarUsuario(usuario1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> usuarioService.registrarUsuario(usuario2));

        assertEquals("Correo duplicado", exception.getMessage());
    }

    @Test
    void consultarUsuariosExitoso() {
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario("Liliana", "Arcos", "arcoslili@gmail.com", "7774562890", "lili123", "Usuario", true);
        usuarioService.registrarUsuario(usuario);

        assertFalse(usuarioService.consultarUsuarios().isEmpty());
        assertEquals("Liliana", usuarioService.consultarUsuarios().get(0).getNombre());
    }

    @Test
    void consultarUsuariosListaVacia() {
        UsuarioService usuarioService = new UsuarioService();

        assertTrue(usuarioService.consultarUsuarios().isEmpty());
    }

    @Test
    void editarUsuarioExitoso() {
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario("Liliana", "Arcos", "arcoslili@gmail.com", "7771234567", "Password123", "Usuario", true);
        usuarioService.registrarUsuario(usuario);

        boolean resultado = usuarioService.editarUsuario(usuario, "Liliana Actualizada");

        assertTrue(resultado);
        assertEquals("Liliana Actualizada", usuario.getNombre());
    }

    @Test
    void editarUsuarioConNombreInvalido() {
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario("Liliana", "Arcos", "arcoslili@gmail.com", "7771234567", "Password123", "Usuario", true);
        usuarioService.registrarUsuario(usuario);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> usuarioService.editarUsuario(usuario, ""));

        assertEquals("El nombre no puede estar vacío", exception.getMessage());
    }

    @Test
    void cambiarEstadoUsuarioHabilitar() {
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario("Carlos", "Méndez", "cmendez@gmail.com", "7771234567", "Password123", "Usuario", false);
        usuarioService.registrarUsuario(usuario);

        usuario.setEstado(true);

        assertEquals(true, usuario.isEstado());
    }

    @Test
    void cambiarEstadoUsuarioDeshabilitar() {
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario("Carlos", "Méndez", "cmendez@gmail.com", "7771234567", "Password123", "Usuario", true);
        usuarioService.registrarUsuario(usuario);

        usuario.setEstado(false);

        assertEquals(false, usuario.isEstado());
    }
}
