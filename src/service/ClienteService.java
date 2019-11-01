/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ClienteDao;
import model.Cliente;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Alunos
 */
public class ClienteService {

    private ClienteDao cDao;

    public ClienteService() {
        cDao = new ClienteDao();
    }

    public List<Vector> makeList(List<Vector> clientesVec, List<Cliente> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            Vector clienteVec = new Vector();
            clienteVec.add(clientes.get(i).getId());
            clienteVec.add(clientes.get(i).getNome());
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            String dataNascimento = dateFormat1.format(clientes.get(i).getDataNascimento().getTime());
            clienteVec.add(dataNascimento);
            clienteVec.add(clientes.get(i).getTelefone());
            clienteVec.add(clientes.get(i).getEmail());
            clientesVec.add(clienteVec);
        }
        return clientesVec;
    }

    public Boolean inserirContato(String nome, Calendar dataNasc, String telefone, String email) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setDataNascimento(dataNasc);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);

        return this.inserirContato(cliente);
    }

    public List<Vector> buscaTodosClientesVec() {
        List<Vector> clientesVec = new ArrayList();
        List<Cliente> clientes = cDao.consultaTodosClientes();
        return makeList(clientesVec, clientes);
    }

    public List<Vector> buscaClientesIdVec(String id) {
        List<Vector> clientesVec = new ArrayList();
        List<Cliente> clientes = cDao.consultaClientesId(id);
        return makeList(clientesVec, clientes);
    }

    public List<Vector> buscaClientesNomeVec(String nome) {
        List<Vector> clientesVec = new ArrayList();
        List<Cliente> clientes = cDao.consultaClientesNome(nome);
        return makeList(clientesVec, clientes);
    }

    public List<Vector> buscaClientesDataVec(Calendar data) {
        List<Vector> clientesVec = new ArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataF = dateFormat.format(data.getTimeInMillis());
        List<Cliente> clientes = cDao.consultaClientesData(dataF);
        return makeList(clientesVec, clientes);
    }

    public Boolean inserirContato(Cliente cliente) {
        return cDao.insereContact(cliente);
    }

    public Boolean verificaCampos(String nome, Calendar dataNasc, String telefone, String email) {
        return nome.equals("") || dataNasc == null || telefone.equals("") || email.equals("");
    }

    public Boolean verificaCamposId(String nome, Calendar dataNasc, String telefone, String email, String id) {
        return nome.equals("") || dataNasc == null || telefone.equals("") || email.equals("") || id.equals("");
    }

    public Boolean updateCliente(String nome, Calendar data, String telefone, String email, Long id) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setDataNascimento(data);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setId(id);
        return cDao.atualizaContato(cliente);
    }

}
