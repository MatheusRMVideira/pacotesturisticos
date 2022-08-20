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
@Table(name = "Compra")
public class Compra extends AbstractEntity<Long>{

    @NotNull(message = "{NotNull.compra.cliente}")
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @NotNull(message = "{NotNull.compra.pacote}")
    @ManyToOne
    @JoinColumn(name = "pacote_id", nullable = false)
    private Pacote pacote;

    @NotNull(message = "{NotNull.compra.valor}")
    @NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.00")
    private BigDecimal valor;

    @NotNull(message = "{NotNull.compra.ativo}")
    @Column(nullable = false)
    private boolean ativo;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
