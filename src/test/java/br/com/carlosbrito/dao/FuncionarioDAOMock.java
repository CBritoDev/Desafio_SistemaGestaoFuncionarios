package br.com.carlosbrito.dao;

import br.com.carlosbrito.domain.Funcionario;
import br.com.carlosbrito.util.Funcao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author carlos.brito
 * Criado em: 09/09/2025
 */
public class FuncionarioDAOMock implements IFuncionarioDAO {

    private Map<Funcao, Map<Integer, Funcionario>> mapaFuncionariosPorFuncao;
    private Map<Integer, Funcionario> listaFuncionarios;

    public FuncionarioDAOMock(){
        this.mapaFuncionariosPorFuncao = new HashMap<>();
        this.listaFuncionarios = new HashMap<>();
    }

    @Override
    public void salvar(Funcionario funcionario) {
        listaFuncionarios.put(funcionario.getId(), funcionario);
        mapaFuncionariosPorFuncao.put(funcionario.getFuncaoENUM(), listaFuncionarios);
        System.out.println(mapaFuncionariosPorFuncao.values());
    }

    @Override
    public Funcionario buscar(int id) {
        try{
            if(listaFuncionarios.containsKey(id)){
                return listaFuncionarios.get(id);
            }else{
                System.out.println("Não foi possível encontrar o funcionário indicado pelo id.");
            }
        }catch(Exception e){
            System.out.println("ERRO: "+ e);
        }
        return null;
    }

    @Override
    public void atualizar(Funcionario funcionarioNovosDados) {
        Funcionario funcionario = funcionarioNovosDados;
        listaFuncionarios.put(funcionario.getId(),funcionario);
        mapaFuncionariosPorFuncao.put(funcionario.getFuncaoENUM(),listaFuncionarios);
    }

    @Override
    public void excluir(int id) {
        if(listaFuncionarios.containsKey(id)){
            mapaFuncionariosPorFuncao.remove(listaFuncionarios.get(id).getFuncaoENUM(), listaFuncionarios.get(id));
            listaFuncionarios.remove(id);
            System.out.println("Funcionário removido com sucesso");
        }
    }

    @Override
    public List<Funcionario> listar() {
        List<Funcionario> listaFuncionariosList =  new ArrayList<>();
        listaFuncionarios.forEach((k,v)-> listaFuncionariosList.add(v));
        return listaFuncionariosList;
    }

    @Override
    public void salvarListaFuncionarios(List<Funcionario> lista) {
        lista.forEach(funcionario -> listaFuncionarios.put(funcionario.getId(),funcionario));
    }

    @Override
    public void atualizarTodosFuncionarios(List<Funcionario> funcionarioNovosDados) {
        Map<Integer, Funcionario> mapaNovosDados = funcionarioNovosDados.stream()
                .filter(funcionario -> listaFuncionarios.containsKey(funcionario.getId()))
                .collect(Collectors.toMap(Funcionario::getId, Function.identity()));
        listaFuncionarios.putAll(mapaNovosDados);
    }
}
