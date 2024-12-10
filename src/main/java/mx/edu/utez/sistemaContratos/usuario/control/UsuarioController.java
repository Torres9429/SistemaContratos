package mx.edu.utez.sistemaContratos.usuario.control;

import mx.edu.utez.sistemaContratos.usuario.model.UsuarioDto;
import mx.edu.utez.sistemaContratos.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = {"*"},methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.save(usuarioDto);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_ACCESS', 'ROLE_USER_ACCESS')")
    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.update(usuarioDto);
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<Message> changeStatus(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.changeStatus(usuarioDto);
    }

    @GetMapping("/actives")
    public ResponseEntity<Message> findActives() {
        return usuarioService.findActives();
    }
    @PostMapping("/send-email")
    public ResponseEntity<Object> sendEmail(@Validated({UsuarioDto.FindByEmail.class}) @RequestBody UsuarioDto dto){
        return usuarioService.sendEmail(dto);
    }

    @PostMapping("/verify-code")
    public ResponseEntity<Object> verifyCode(@Validated({UsuarioDto.VerifyCode.class}) @RequestBody UsuarioDto dto){
        return usuarioService.verifyCode(dto);
    }
    @PostMapping("/verify-password")
    public ResponseEntity<Message> verifyPassword(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.verifyPassword(usuarioDto);
    }
    @PutMapping("/change-password")
    public ResponseEntity<Message> changePassword(@RequestBody UsuarioDto usuarioDto){
        return usuarioService.cambiarContra(usuarioDto);
    }
    @PutMapping("/change-password-gral")
    public ResponseEntity<Message> changePasswordUser(@RequestBody UsuarioDto usuarioDto){
        return usuarioService.cambiarContraGral(usuarioDto);
    }


}
