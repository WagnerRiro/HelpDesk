package br.com.fenda.helpdesk.domain;

import br.com.fenda.helpdesk.domain.dtos.TecnicoDTO;
import br.com.fenda.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa{
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(TecnicoDTO tecnicoDTO) {
        this.id = tecnicoDTO.getId();
        this.nome = tecnicoDTO.getNome();
        this.cpf = tecnicoDTO.getCpf();
        this.email = tecnicoDTO.getEmail();
        this.senha = tecnicoDTO.getSenha();
        this.perfis = tecnicoDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = tecnicoDTO.getDataCriacao();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
