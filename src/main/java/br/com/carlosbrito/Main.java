package br.com.carlosbrito;

import br.com.carlosbrito.dao.FuncionarioDAO;
import br.com.carlosbrito.dao.IFuncionarioDAO;
import br.com.carlosbrito.domain.Funcionario;
import br.com.carlosbrito.service.FuncionarioService;
import br.com.carlosbrito.service.IFuncionarioService;
import br.com.carlosbrito.util.Funcao;
import br.com.carlosbrito.util.FuncionarioUtil;
import br.com.carlosbrito.domain.ListaFuncionarios;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.carlosbrito.util.FuncionarioUtil.encontraFuncionarioMaisVelho;

/**
 * @author carlos.brito
 * Criado em: 09/09/2025
 */
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IFuncionarioDAO funcionarioDAO =  new FuncionarioDAO();
        IFuncionarioService funcionarioService =  new FuncionarioService(funcionarioDAO);
        ListaFuncionarios listaFuncionarios = new ListaFuncionarios();

        //Requisito 3.1 -  Adiciona todos os funcionarios da tabela, na ordem estabelecida
        funcionarioService.salvarListaFuncionarios(listaFuncionarios.getListaFuncionarios());

        //Requisito 3.2 e 3.3 -  Remover funcionario João da  base de dados.
        System.out.println("-------------------- Lista com funcionário João --------------------");
        FuncionarioUtil.listarFuncionarios(funcionarioService.listar());
        FuncionarioUtil.excluirFuncionario(funcionarioService, "João");
        System.out.println("-------------------- Lista sem funcionário João --------------------");
        FuncionarioUtil.listarFuncionarios(funcionarioService.listar());

        //Requisito 3.4 - Todos receberam aumento de 10% e lista é atualizada
        System.out.println("-------------------- Lista com sálarios antigos --------------------");
        FuncionarioUtil.listarFuncionarios(funcionarioService.listar());
        System.out.println("-------------------- Lista com sálarios atualizados --------------------");
        FuncionarioUtil.atualizarTodos(funcionarioService.listar(), 10.0);
        FuncionarioUtil.listarFuncionarios(funcionarioService.listar());

        //Requisito 3.5 e 3.6
        System.out.println("-------------------- Funcionários agrupados por função --------------------");
        Map<Funcao, List<Funcionario>> mapaAgrupado = FuncionarioUtil.separarPorFuncao(funcionarioService.listar());
        FuncionarioUtil.listarFuncionarios(mapaAgrupado);

        //Requisito 3.8
        System.out.println("-------------------- Funcionários por mês de aniversário--------------------");
        List<Funcionario> aniversarianteMes = FuncionarioUtil.separaPorDataAniversario(funcionarioService.listar(), 10);
        FuncionarioUtil.listarFuncionarios(aniversarianteMes);
        List<Funcionario> aniversarianteMes2 = FuncionarioUtil.separaPorDataAniversario(funcionarioService.listar(), 12);
        FuncionarioUtil.listarFuncionarios(aniversarianteMes2);

        //3.9 Encontra funcionario com maior idade com atributos nome e idade
        System.out.println("-------------------- Funcionários com mais idade--------------------");
        FuncionarioUtil.encontraFuncionarioMaisVelho(funcionarioService.listar());

        //3.10 Imprimir em ordem alfabetica
        System.out.println("-------------------- Funcionários em ordem alfabetica--------------------");
        List<Funcionario> ordemAlfabetica = FuncionarioUtil.ordemAlfabetica(funcionarioService.listar());
        FuncionarioUtil.listarFuncionarios(ordemAlfabetica);

        // 3.11 Imprimir total de salario dos funcionarios
        System.out.println("-------------------- Imprime total salário funcionários--------------------");
        BigDecimal total = FuncionarioUtil.totalSalarios(funcionarioService.listar());
        System.out.println("Valor totla de salários: "+ total);

        //3.12 Imprimir total de salários minimos de cada pessoa
        System.out.println("-------------------- Imprime total salário minimo para cada funcionário--------------------");
        Map<String, BigDecimal> salarioMinPorFuncionario = FuncionarioUtil.calculaSalariosMinimos(funcionarioService.listar());
        FuncionarioUtil.listarFuncionariosBaseSalario(salarioMinPorFuncionario);

    }

}


