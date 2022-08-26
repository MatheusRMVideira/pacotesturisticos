package br.ufscar.dc.dsw.pacotesturisticos.domain;

import java.math.BigDecimal;
import java.util.Base64;

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
    @Column(nullable = true)
    private byte[] byteStream;

    @NotNull(message = "{NotNull.imagem.tipo}")
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;

    
    public byte[] getByteStream(){
        return byteStream;
    }

    public void setByteStream(byte[] byteStream){
        this.byteStream = byteStream;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBase64(){
        return new String(Base64.getEncoder().encode(byteStream));
    }
}
