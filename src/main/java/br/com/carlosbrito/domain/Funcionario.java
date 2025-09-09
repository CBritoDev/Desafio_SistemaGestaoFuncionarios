package br.com.carlosbrito.domain;

import br.com.carlosbrito.util.FormatadorBigDecimal;
import br.com.carlosbrito.util.Funcao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author carlos.brito
 * Criado em: 09/09/2025
 */
public class Funcionario extends Pessoa{
    private int id;
    private BigDecimal salario;
    private String funcao;
    private String dataNascimentoFormatada;

    public Funcionario(int id, String nome, LocalDate dataNascimento, BigDecimal salario, Funcao funcao) {
        super(nome.toUpperCase(), dataNascimento);
        this.id = id;
        this.salario = salario;
        this.funcao = funcao.getNomeFuncao();
        this.dataNascimentoFormatada = dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public Funcao getFuncaoENUM(){
        return Funcao.valueOf(this.funcao.toUpperCase());
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getDataNascimentoFormatada() {
        return dataNascimentoFormatada;
    }

    public void setDataNascimentoFormatada(String dataNascimentoFormatada) {
        this.dataNascimentoFormatada = dataNascimentoFormatada;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return id == that.id && Objects.equals(salario, that.salario) && Objects.equals(funcao, that.funcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salario, funcao);
    }

    @Override
    public String toString(){
        return String.format(
                "Identificador:%d | Nome: %s | Data de Nascimento:%s | Cargo:%s | Salário:%s",
                id,getNome(),dataNascimentoFormatada,getFuncao(), FormatadorBigDecimal.formatar(salario)
        );
    }
}
