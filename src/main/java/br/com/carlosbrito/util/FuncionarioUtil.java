package br.com.carlosbrito.util;

import br.com.carlosbrito.domain.Funcionario;
import br.com.carlosbrito.service.IFuncionarioService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author carlos.brito
 * Criado em: 09/09/2025
 */
public class FuncionarioUtil {

    public static void atualizarTodos(List<Funcionario> funcionarios, Double valorDeAumento){
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().add(multiplicador(valorDeAumento, funcionario.getSalario()))));
    }

    public static void atualizarUnico(List<Funcionario> funcionarios, String nomeFuncionario , IFuncionarioService service, Double valorDeAumento){

        funcionarios.stream()
                .filter(funcionario -> funcionario.getNome().equalsIgnoreCase(nomeFuncionario))
                .findFirst()
                .ifPresent(funcionario -> {
                    funcionario.setSalario(funcionario.getSalario().add(multiplicador(valorDeAumento, funcionario.getSalario())));
                    service.atualizar(funcionario);
                });
    }


    public static Map<Funcao, List<Funcionario>> separarPorFuncao(List<Funcionario> funcionarios){
        Map<Funcao, List<Funcionario>> mapaFuncionarios = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncaoENUM));
        return mapaFuncionarios;
    }


    //Auxilia o metodo listar a printar as informações dos funcionários
    public static void listarFuncionarios(List<Funcionario> lista){
        if(lista.isEmpty()){
            System.out.println("Não encontrado na base de dados.");
        }else{
            for(Funcionario funcionario : lista){
                System.out.println(funcionario);
                System.out.println("");
            }
        }

    }

    //Auxilia o metodo listar a printar as informações dos funcionários
    public static void listarFuncionarios(Map<Funcao, List<Funcionario>> mapaFuncionarios){
        mapaFuncionarios.forEach((k,v) -> {
            System.out.println("Função " + k.getNomeFuncao());
            listarFuncionarios(v);
        });
    }

    //Auxilia o metodo listar a printar as informações dos funcionarios
    public static void listarFuncionariosBaseSalario(Map<String, BigDecimal> mapaFuncionarios){
        mapaFuncionarios.forEach((k,v) -> {
            System.out.println("Funcionário " + k + " | Quantidade sálarios mínimos: " + v);
        });
    }


    public static void excluirFuncionario(IFuncionarioService funcionarioService,String nome){
        //Recebe a lista de funcionarios pelo service
        List<Funcionario> listaFuncionarios = funcionarioService.listar();
        //Filtra com Stream o nome do funcionario que deseja encontrar filtrando-o e guardando numa lista de 1 elemento
        List<Funcionario> funcionarios = listaFuncionarios.stream()
                .filter(funcionario -> funcionario.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());

        //Verifica se a lista contem o primeiro elemento, que seria o funcionario indicado.
        if(funcionarios.contains(funcionarios.getFirst())){
            funcionarioService.excluir(funcionarios.getFirst().getId());
            System.out.println("Nome funcionario removido: " + nome);
        }else{
            System.out.println("Funcionário " + nome + " não encontrado na base de dados.");
        }
    }

    private static BigDecimal multiplicador(Double valorDeAumento, BigDecimal valorAnterior){
        BigDecimal resultado = BigDecimal.valueOf(valorDeAumento).divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        return valorAnterior.multiply((resultado)).setScale(2, RoundingMode.HALF_UP);
    }

    public static List<Funcionario> separaPorDataAniversario(List<Funcionario> funcionarios,int mesAniversario){
        Month mesAComparar = Month.of(mesAniversario);

        return funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonth().equals(mesAComparar))
                .collect(Collectors.toList());
    }

    public static void encontraFuncionarioMaisVelho(List<Funcionario> funcionarios){

        Funcionario funcionario = funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento)).orElse(null);

        LocalDate hoje =  LocalDate.now();
        LocalDate dataNascimentoFunc = funcionario.getDataNascimento();

        Period periodo = Period.between(dataNascimentoFunc, hoje);
        Integer anosDiferenca = periodo.getYears();

        System.out.println("Funcionário masi velho: " + funcionario.getNome() + " com " + anosDiferenca + " anos de idade.");

    }

    public static List<Funcionario> ordemAlfabetica(List<Funcionario> funcionarios){
        List<Funcionario> funcionariosOrdemAlfabetica = new ArrayList<>(funcionarios);
        funcionariosOrdemAlfabetica.sort(Comparator.comparing(Funcionario::getNome));
        return funcionariosOrdemAlfabetica;
    }

    public static BigDecimal totalSalarios(List<Funcionario> funcionarios){

        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Map<String, BigDecimal> calculaSalariosMinimos(List<Funcionario> funcionarios){
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        Map<String, BigDecimal> salarioMinimosFuncionario = new HashMap<>();
        funcionarios.forEach(funcionario -> {
            salarioMinimosFuncionario.put(funcionario.getNome(),funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP));
        });
        return salarioMinimosFuncionario;
    }


}
