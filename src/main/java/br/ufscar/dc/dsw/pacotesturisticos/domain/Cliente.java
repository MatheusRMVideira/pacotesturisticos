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
import br.ufscar.dc.dsw.pacotesturisticos.validation.UniqueCpf;
import br.ufscar.dc.dsw.pacotesturisticos.validation.OnUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends AbstractEntity<Long>{
    
    @NotBlank(message = "{NotBlank.cliente.email}")
    @UniqueEmail(groups= OnUpdate.class, message = "{UniqueEmail.cliente.email}")
    @Size(max = 255, message = "{Size.cliente.email}")
    @Column(nullable = false, length = 255, unique=true)
    private String email;

    @NotBlank(message = "{NotBlank.cliente.senha}")
    @Size(max = 255, message = "{Size.cliente.senha}")
    @Column(nullable = false, length = 255)
    private String senha;

    @NotBlank(message = "{NotBlank.cliente.nome}")
    @Size(max = 50, message = "{Size.cliente.nome}")
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "{NotBlank.cliente.cpf}")
    @Size(max = 14, message = "{Size.cliente.cpf}")
    @UniqueCpf(groups= OnUpdate.class, message = "{UniqueCpf.cliente.cpf}")
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @NotBlank(message = "{NotBlank.cliente.telefone}")
    @Size(max = 14, message = "{Size.cliente.telefone}")
    @Column(nullable = false, length = 14)
    private String telefone;

    @NotNull(message = "{NotNull.cliente.sexo}")
    @Column(nullable = false)
    private char sexo;

    @NotBlank(message = "{NotBlank.cliente.dataNascimento}")
    @Size(max = 10, message = "{Size.cliente.dataNascimento}")
    @Column(nullable = false, length = 10)
    private String dataNascimento;

    @NotBlank(message = "{NotBlank.cliente.tipo}")
    @Column(nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
}
