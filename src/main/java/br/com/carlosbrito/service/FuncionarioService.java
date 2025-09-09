package br.com.carlosbrito.service;

import br.com.carlosbrito.dao.IFuncionarioDAO;
import br.com.carlosbrito.domain.Funcionario;

import java.util.List;

/**
 * @author carlos.brito
 * Criado em: 09/09/2025
 */
public class FuncionarioService implements IFuncionarioService {

    public IFuncionarioDAO funcionarioDao;

    public FuncionarioService(IFuncionarioDAO funcionarioDao){
        this.funcionarioDao = funcionarioDao;
    }

    @Override
    public void salvar(Funcionario funcionario) {
         funcionarioDao.salvar(funcionario);
    }

    @Override
    public Funcionario buscar(int id) {
        return funcionarioDao.buscar(id);
    }

    @Override
    public void atualizar(Funcionario funcionarioNovosDados) {
        funcionarioDao.atualizar(funcionarioNovosDados);
    }

    @Override
    public void excluir(int id) {
        funcionarioDao.excluir(id);
    }

    @Override
    public List<Funcionario> listar() {
        return funcionarioDao.listar();
    }

    @Override
    public void salvarListaFuncionarios(List<Funcionario> listaBase) {
        funcionarioDao.salvarListaFuncionarios(listaBase);
    }

    @Override
    public void atualizarTodosFuncionarios(List<Funcionario> listaNovosDados) {
        funcionarioDao.atualizarTodosFuncionarios(listaNovosDados);
    }
}
