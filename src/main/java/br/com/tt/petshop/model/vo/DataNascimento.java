package br.com.tt.petshop.model.vo;

import br.com.tt.petshop.exception.BusinessException;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class DataNascimento {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_nascimento")
    @PastOrPresent
    private LocalDate data;

    public DataNascimento() {
    }

    public DataNascimento(LocalDate dataNascimento) {
        this.data = dataNascimento;
    }

    public boolean isValida() throws BusinessException {
        if(Objects.isNull(data) || LocalDate.now().isBefore(data)){
            return false;
        }
        return true;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
