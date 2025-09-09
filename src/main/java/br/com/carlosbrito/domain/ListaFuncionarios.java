package br.com.carlosbrito.domain;

import br.com.carlosbrito.util.Funcao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author carlos.brito
 * Criado em: 09/09/2025
 */
public class ListaFuncionarios {

    private List<Funcionario> listaFuncionarios;

    public ListaFuncionarios(){
        //Preenche a lista com seguintes dados respectivamente:
        //id,nome,data de nascimento, salario,funcao
        listaFuncionarios = Arrays.asList(
                new Funcionario(0,"Maria", LocalDate.of(2000,10,18),BigDecimal.valueOf(2009.84), Funcao.OPERADOR),
                new Funcionario(1,"João", LocalDate.of(1990,5,12),BigDecimal.valueOf(2284.38),Funcao.OPERADOR),
                new Funcionario(2,"Caio", LocalDate.of(1961,5,2),BigDecimal.valueOf(9836.14),Funcao.COORDENADOR),
                new Funcionario(3,"Miguel", LocalDate.of(1988,10,14),BigDecimal.valueOf(19119.88),Funcao.DIRETOR),
                new Funcionario(4,"Alice", LocalDate.of(1995,1, 5),BigDecimal.valueOf(2234.68),Funcao.RECEPCIONISTA),
                new Funcionario(5,"Heitor", LocalDate.of(1999,11,19),BigDecimal.valueOf(1582.72),Funcao.OPERADOR),
                new Funcionario(6,"Arthur", LocalDate.of(1993,3,31),BigDecimal.valueOf(4071.84),Funcao.CONTADOR),
                new Funcionario(7,"Laura", LocalDate.of(1994,7,8),BigDecimal.valueOf(3017.45),Funcao.GERENTE),
                new Funcionario(8,"Heloisa", LocalDate.of(2003,5,24),BigDecimal.valueOf(1606.85),Funcao.ELETRICISTA),
                new Funcionario(9,"Helena", LocalDate.of(1996,9,2),BigDecimal.valueOf(2799.93),Funcao.GERENTE));

    }


    public List<Funcionario> getListaFuncionarios(){
        return listaFuncionarios;
    }
}
