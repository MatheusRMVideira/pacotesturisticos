package br.ufscar.dc.dsw.pacotesturisticos.domain;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import br.ufscar.dc.dsw.pacotesturisticos.validation.UniqueEmail;
import br.ufscar.dc.dsw.pacotesturisticos.validation.UniqueCnpj;

@SuppressWarnings("serial")
@Entity
@Table(name = "Agencia")
public class Agencia extends AbstractEntity<Long>{

    @NotBlank(message = "{NotBlank.agencia.email}")
    @UniqueEmail(message = "{UniqueEmail.agencia.email}")
    @Size(max = 255, message = "{Size.agencia.email}")
    @Column(nullable = false, length = 255)
    private String email;

    @NotBlank(message = "{NotBlank.agencia.senha}")
    @Size(max = 255, message = "{Size.agencia.senha}")
    @Column(nullable = false, length = 255)
    private String senha;

    @NotBlank(message = "{NotBlank.agencia.nome}")
    @Size(max = 50, message = "{Size.agencia.nome}")
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "{NotBlank.agencia.cnpj}")
    @Size(max = 18, message = "{Size.agencia.cnpj}")
    @UniqueCnpj(message = "{UniqueCnpj.agencia.cnpj}")
    @Column(nullable = false, length = 18)
    private String cnpj;

    @NotBlank(message = "{NotBlank.agencia.descricao}")
    @Size(max = 255, message = "{Size.agencia.descricao}")
    @Column(nullable = false, length = 255)
    private String descricao;

    @OneToMany(mappedBy = "agencia")
    private List<Pacote> pacotes;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Pacote> getPacotes() {
        return pacotes;
    }

    public void setPacotes(List<Pacote> pacotes) {
        this.pacotes = pacotes;
    }
    

}
