package br.com.mvc.projectmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvc.projectmvc.dto.CadastroDto;
import br.com.mvc.projectmvc.model.User;
import br.com.mvc.projectmvc.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("cadastro")
public class CadastroController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/formulario")
    public String formulario(CadastroDto cadastroDto){
        return "Cadastro/formulario";
    }

    @PostMapping("/novo")
    public String novoUsuario(@Valid CadastroDto cadastroDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "Cadastro/formulario";
        }
        User user = cadastroDto.toUser();
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login?cadastro=sucesso";
    }
}