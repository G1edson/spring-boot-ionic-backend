package com.gledson.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gledson.cursomc.domain.Categoria;
import com.gledson.cursomc.domain.Cidade;
import com.gledson.cursomc.domain.Cliente;
import com.gledson.cursomc.domain.Endereco;
import com.gledson.cursomc.domain.Estado;
import com.gledson.cursomc.domain.ItemPedido;
import com.gledson.cursomc.domain.Pagamento;
import com.gledson.cursomc.domain.PagamentoComBoleto;
import com.gledson.cursomc.domain.PagamentoComCartao;
import com.gledson.cursomc.domain.Pedido;
import com.gledson.cursomc.domain.Produto;
import com.gledson.cursomc.domain.enums.EstadoPagamento;
import com.gledson.cursomc.domain.enums.TipoCliente;
import com.gledson.cursomc.repositories.CategoriaRepository;
import com.gledson.cursomc.repositories.CidadeRepository;
import com.gledson.cursomc.repositories.ClienteRepository;
import com.gledson.cursomc.repositories.EnderecoRepository;
import com.gledson.cursomc.repositories.EstadoRepository;
import com.gledson.cursomc.repositories.ItemPedidoRepository;
import com.gledson.cursomc.repositories.PagamentoRepository;
import com.gledson.cursomc.repositories.PedidoRepository;
import com.gledson.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteReposity;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cozinha");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 90.00);
		Produto p4 = new Produto(null, "Panela de Pressão", 120.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p4));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat3));
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Rondônia");
		Estado est3 = new Estado(null, "Espírito Santo");
		Estado est4 = new Estado(null, "Bahia");
		
		Cidade cid1 = new Cidade(null, "Ji-Paraná");
		Cidade cid2 = new Cidade(null, "Porto Velho");
		Cidade cid3 = new Cidade(null, "Ouro Preto");
		Cidade cid4 = new Cidade(null, "Vitória");
		Cidade cid5 = new Cidade(null, "Vitória");
		Cidade cid6 = new Cidade(null, "Santos");
		Cidade cid7 = new Cidade(null, "São Paulo");
		
		est1.getCidades().addAll(Arrays.asList(cid6, cid7));
		est2.getCidades().addAll(Arrays.asList(cid1, cid2, cid3));
		est3.getCidades().addAll(Arrays.asList(cid4));
		est4.getCidades().addAll(Arrays.asList(cid5));

		cid1.setEstado(est2);
		cid2.setEstado(est2);
		cid3.setEstado(est2);
		cid4.setEstado(est3);
		cid5.setEstado(est4);
		cid6.setEstado(est1);
		cid7.setEstado(est1);
		
		Cliente cli1 = new Cliente(null, "Gledson Pereira de Miranda", "g1edson@hotmail.com", "112.526.287-73", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Mario Algusto", "mario@hotmail.com", "111.222.333-44", TipoCliente.PESSOAFISICA);
		Cliente cli3 = new Cliente(null, "Maria Fernandez", "maria@hotmail.com", "555.444.333-22", TipoCliente.PESSOAFISICA);
		Cliente cli4 = new Cliente(null, "Casa do Boi", "boi@hotmail.com", "99.999.9999-99", TipoCliente.PESSOAJURIDICA);
		
		Endereco end1 = new Endereco(null, "Rua Ipê", "3442", "em frente uma Igreja", "Valparaiso", "76908-768", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Brasil", "42", "", "Nova Brasilia", "76908-755", cli2, cid1);
		Endereco end3 = new Endereco(null, "Rua Catarina", "34", "em frente uma Igreja", "Urupa", "76908-766", cli3, cid2);
		Endereco end4 = new Endereco(null, "Rua Algusta", "344", "em frente ao camelo", "Centro", "16908-768", cli4, cid7);
		
		cli1.getTelefones().addAll(Arrays.asList("99959-4159", "99943-0387"));
		cli1.getEnderecos().addAll(Arrays.asList(end1));
		
		cli2.getTelefones().addAll(Arrays.asList("9993-2258"));
		cli2.getEnderecos().addAll(Arrays.asList(end2, end3, end4));
		
		cli3.getTelefones().addAll(Arrays.asList("99955-6545"));
		cli3.getEnderecos().addAll(Arrays.asList(end3));
		
		cli4.getTelefones().addAll(Arrays.asList("2214-6587", "3654-4587"));
		cli4.getEnderecos().addAll(Arrays.asList(end4));
		
	
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3, est4));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3, cid4, cid5, cid6, cid7));
		clienteReposity.saveAll(Arrays.asList(cli1, cli2, cli3, cli4));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1 );
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2 );
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2, ip3));
	}
}
