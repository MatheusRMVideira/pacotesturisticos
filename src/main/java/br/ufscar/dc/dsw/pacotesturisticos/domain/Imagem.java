package br.ufscar.dc.dsw.pacotesturisticos.domain;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "Imagem")
public class Imagem extends AbstractEntity<Long>{
    
    @Lob
    @Basic(fetch = javax.persistence.FetchType.LAZY)
    @NotNull(message = "{NotNull.imagem.byteArray}")
    @Column(nullable = false)
    private byte[] byteArray;

    @ManyToOne
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }
    
}
