/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.PedidoDao;
import model.Pedido;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Alunos
 */
public class PedidoService {

    private PedidoDao pDao;

    public PedidoService() {
        pDao = new PedidoDao();
    }

    public List<Vector> makeList(List<Pedido> pedidos, List<Vector> pedidosVec) {
        for (int i = 0; i < pedidos.size(); i++) {
            Vector pedidoVec = new Vector();
            pedidoVec.add(pedidos.get(i).getId());
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            String dataEntrada = dateFormat1.format(pedidos.get(i).getDataEntrada().getTime());
            pedidoVec.add(dataEntrada);
            pedidoVec.add(pedidos.get(i).getTipoServico());
            pedidoVec.add(pedidos.get(i).getValor());
            pedidoVec.add(pedidos.get(i).getPlaca());
            pedidosVec.add(pedidoVec);
        }
        return pedidosVec;
    }

    public Boolean inserirPedido(Calendar dataEntrada, String tipoServico, Double valor, String placa) {
        Pedido pedido = new Pedido();
        pedido.setDataEntrada(dataEntrada);
        pedido.setTipoServico(tipoServico);
        pedido.setValor(valor);
        pedido.setPlaca(placa);
        return this.inserirPedido(pedido);
    }

    public Boolean inserirPedido(Pedido pedido) {
        return pDao.inserePedido(pedido);
    }

    public List<Vector> buscaPedidosDatasVec(Calendar data1, Calendar data2) {
        List<Vector> pedidosVec = new ArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data1S = dateFormat.format(data1.getTime());
        String data2S = dateFormat.format(data2.getTime());
        List<Pedido> pedidos = pDao.consultaPedidosDatas(data1S, data2S);
        return makeList(pedidos, pedidosVec);
    }

    public List<Vector> buscaPedidosIdVec(String id) {
        List<Vector> pedidosVec = new ArrayList();
        List<Pedido> pedidos = pDao.consultaPedidosID(id);
        return makeList(pedidos, pedidosVec);
    }
    
     public List<Vector> buscaTodosPedidosVec() {
        List<Vector> pedidosVec = new ArrayList();
        List<Pedido> pedidos = pDao.consultaTodosPedidos();
        return makeList(pedidos, pedidosVec);
    }

    public List<Vector> buscaPedidosDataVec(Calendar data1) {
        List<Vector> pedidosVec = new ArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data1S = dateFormat.format(data1.getTime());
        List<Pedido> pedidos = pDao.consultaPedidosData(data1S);
        return makeList(pedidos, pedidosVec);
    }

    public List<Vector> buscaPedidosTipoVec(String tipo) {
        List<Vector> pedidosVec = new ArrayList();
        List<Pedido> pedidos = pDao.consultaPedidosTipo(tipo);
        return makeList(pedidos, pedidosVec);
    }

    public List<Vector> buscaPedidosPlacaVec(String placa) {
        List<Vector> pedidosVec = new ArrayList();
        List<Pedido> pedidos = pDao.consultaPedidosPlaca(placa);
        return makeList(pedidos, pedidosVec);
    }

    public boolean verificaCampos(Calendar dataEntrada, String tipoServico, String stringValor, String placa) {
        return dataEntrada == null || tipoServico.equals("") || stringValor.equals("") || placa.equals("");
    }
    
     public Boolean verificaCamposId(Calendar dataEntrada, String tipoServico, String valor, String placa, String id) {
        return dataEntrada == null || tipoServico.equals("") || valor.equals("") || placa.equals("") || id.equals("");
    }
    
      public Boolean updateCliente(Calendar dataEntrada, String tipoServico, Double valor, String placa, Long id) {
        Pedido pedido = new Pedido();
        pedido.setDataEntrada(dataEntrada);
        pedido.setTipoServico(tipoServico);
        pedido.setValor(valor);
        pedido.setPlaca(placa);
        pedido.setId(id);
        return pDao.atualizaPedido(pedido);
    }

}
