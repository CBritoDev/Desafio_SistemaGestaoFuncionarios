package br.com.carlosbrito;

import br.com.carlosbrito.dao.FuncionarioDAOMock;
import br.com.carlosbrito.dao.IFuncionarioDAO;
import br.com.carlosbrito.domain.Funcionario;
import br.com.carlosbrito.service.FuncionarioService;
import br.com.carlosbrito.service.IFuncionarioService;
import br.com.carlosbrito.util.Funcao;
import br.com.carlosbrito.util.FuncionarioUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author carlos.brito
 * Criado em: 09/09/2025
 */
public class FuncionarioTest {

    IFuncionarioService funcionarioService;
    LocalDate dataNascimento =  LocalDate.of(2000,10,18);
    BigDecimal salario =  new BigDecimal("2009.44");
    Funcionario funcionario = new Funcionario(1,"Maria", dataNascimento,salario, Funcao.OPERADOR);

    LocalDate dataNascimentoJoao = LocalDate.of(1990,05,12);
    BigDecimal salarioJoao =  new BigDecimal("2284.38");
    Funcionario funcionarioNovosDados = new Funcionario(2,"João", dataNascimentoJoao,salarioJoao, Funcao.OPERADOR);


    public FuncionarioTest(){
        IFuncionarioDAO dao =  new FuncionarioDAOMock();
        funcionarioService = new FuncionarioService(dao);
    }

    @Before
    public void init(){

    }


    @Test
    public void cadastraFuncionarioTest(){
        funcionarioService.salvar(funcionario);
    }

    @Test
    public void buscarFuncionarioTest(){
        funcionarioService.salvar(funcionario);
        Funcionario retornoEsperado = funcionarioService.buscar(funcionario.getId());
        Assert.assertEquals("Retorno deve ser o mesmo funcionario",retornoEsperado, funcionario);
    }

    @Test
    public void atualizarFuncionarioTest(){
        funcionarioService.salvar(funcionario);
        LocalDate dataNascimentoNovo= LocalDate.of(2000,05,12);
        BigDecimal salarioNovo=  new BigDecimal("2009.44");
        Funcionario funcionarioNovosDados = new Funcionario(1,"Maria", dataNascimentoNovo,salarioNovo, Funcao.OPERADOR);
        funcionarioService.atualizar(funcionarioNovosDados);
        Funcionario retornoEsperado = funcionarioService.buscar(funcionario.getId());
        Assert.assertEquals("Retorno deve ser o mesmo funcionario, com dados atualizados",retornoEsperado, funcionarioNovosDados);

    }

    @Test
    public void deletarFuncionarioTest(){
        funcionarioService.salvar(funcionario);
        funcionarioService.excluir(funcionario.getId());
        Funcionario retorno = funcionarioService.buscar(funcionario.getId());
        Assert.assertNull("Retorno deve ser nulo após exclusão",retorno);
    }

    @Test
    public void listarFuncionariosTest(){
        funcionarioService.salvar(funcionario);
        funcionarioService.salvar(funcionarioNovosDados);
        List<Funcionario> listaBase = Arrays.asList(funcionario,funcionarioNovosDados);
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        listaFuncionarios.addAll(funcionarioService.listar());
        Assert.assertTrue(listaFuncionarios.containsAll(listaBase));
    }

    @Test
    public void salvarListaFuncionariosTest(){
        List<Funcionario> listaBase = Arrays.asList(funcionario,funcionarioNovosDados);
        funcionarioService.salvarListaFuncionarios(listaBase);
        List<Funcionario> listaRetorno = funcionarioService.listar();
        Assert.assertTrue(listaRetorno.containsAll(listaBase));
    }

    @Test
    public void atualizadorDeSalarioTest(){
        List<Funcionario> listaBase = Arrays.asList(funcionario,funcionarioNovosDados);
        FuncionarioUtil.atualizarTodos(listaBase,10.0);
    }


    @Test
    public void atualizarTodosFuncionariosTest(){
        List<Funcionario> listaBase = Arrays.asList(funcionario,funcionarioNovosDados);
        funcionarioService.salvarListaFuncionarios(listaBase);
        BigDecimal salarioNovo =  new BigDecimal("3000.00");


        LocalDate dataNascimento =  LocalDate.of(2000,10,18);
        Funcionario funcionario1 = new Funcionario(1,"Maria", dataNascimento,salarioNovo, Funcao.OPERADOR);

        LocalDate dataNascimentoJoao = LocalDate.of(1990,05,12);
        Funcionario funcionarioNovosDados1 = new Funcionario(2,"João", dataNascimentoJoao,salarioNovo, Funcao.OPERADOR);

        List<Funcionario> listaNovosDados = Arrays.asList(funcionario1, funcionarioNovosDados1);

        funcionarioService.atualizarTodosFuncionarios(listaNovosDados);
        List<Funcionario> retorno = funcionarioService.listar();
        Assert.assertNotEquals("As listas devem ser diferentes",listaBase, retorno);
    }





}
