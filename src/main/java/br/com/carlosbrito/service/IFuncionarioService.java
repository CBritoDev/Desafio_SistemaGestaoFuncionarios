package br.com.carlosbrito.service;

import br.com.carlosbrito.domain.Funcionario;

import java.util.List;

/**
 * @author carlos.brito
 * Criado em: 09/09/2025
 */
public interface IFuncionarioService {

    void salvar(Funcionario funcionario);
    Funcionario buscar(int id);

    void atualizar(Funcionario funcionarioNovosDados);

    void excluir(int id);

     List<Funcionario> listar();

    void salvarListaFuncionarios(List<Funcionario> listaBase);

    void atualizarTodosFuncionarios(List<Funcionario> listaNovosDados);
}
