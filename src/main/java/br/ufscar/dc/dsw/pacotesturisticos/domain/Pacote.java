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

@SuppressWarnings("serial")
@Entity
@Table(name = "Pacote")
public class Pacote extends AbstractEntity<Long>{
    
    @NotBlank(message = "{NotBlank.pacote.cidade}")
    @Size(max = 50, message = "{Size.pacote.cidade}")
    @Column(nullable = false, length = 50)
    private String cidade;

    @NotBlank(message = "{NotBlank.pacote.estado}")
    @Size(max = 50, message = "{Size.pacote.estado}")
    @Column(nullable = false, length = 50)
    private String estado;

    @NotBlank(message = "{NotBlank.pacote.pais}")
    @Size(max = 50, message = "{Size.pacote.pais}")
    @Column(nullable = false, length = 50)
    private String pais;

    @NotBlank(message = "{NotBlank.pacote.partida}")
    @Size(max = 50, message = "{Size.pacote.partida}")
    @Column(nullable = false, length = 50)
    private String partida;

    @NotNull(message = "{NotNull.pacote.duracao}")
    @NumberFormat(style = Style.NUMBER)
    @Column(nullable = false)
    private int duracao;

    @NotNull(message = "{NotNull.pacote.preco}")
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.00")
    private BigDecimal preco;

    @NotBlank(message = "{NotBlank.pacote.descricao}")
    @Size(max = 255, message = "{Size.pacote.descricao}")
    @Column(nullable = false, length = 255)
    private String descricao;

    @OneToMany(mappedBy = "pacote")
    private List<Imagem> imagens;

    @ManyToOne
    @JoinColumn(name = "agencia_id")
    private Agencia agencia;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
}
