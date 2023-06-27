
//import java.util.LinkedList;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.dao.PessoaDAO;
import model.dao.ProdutoDAO;
import model.entity.Pessoa;
import model.entity.Produto;

public class App {
    public static String leString(String msg) {
        String valor = JOptionPane.showInputDialog(null, msg);
        return valor;
    }

    public static int menu() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("1- Inserir Pessoa");
        System.out.println("2- Listar todas Pessoas");
        System.out.println("3- Listar Pessoas por ID");
        System.out.println("4- Deletar Pessoas por id");
        System.out.println("5- Atualizar registro");
        System.out.println("6- Inserir Veiculo");
        System.out.println("7- Listar Veiculo por id");
        System.out.println("8- Deletar Veiculo por id");
        System.out.println("9- Atualizar registro");
        System.out.println("10- Sair");
        System.out.print("Digite: ");
        return teclado.nextInt();
    }

    public static void metodoInserir() {
        String nome = leString("Digite nome");
        String email = leString("Digite e-mail");
        Pessoa pessoa = new Pessoa(nome, email);
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.inserir(pessoa);
    }
    public static void metodoInserirVeiculo() {
    	String numeroChassi = leString("Digite Veiculo");
    	String placa = leString("Digite placa");
    	String modelo = leString("Digite modelo");
    	String nome = leString("Digite nome");
    	String valorSt = leString("Digite valor");
    	double valor = Double.parseDouble(valorSt);
    	Produto produto = new Produto(numeroChassi,placa,modelo,nome,valor);
    	ProdutoDAO p = new ProdutoDAO();
    	p.inserir(produto);
    }

    public static void metodoUpdate(Pessoa p) {
    	String nomeAntigo = p.getNome();
        String emailAntigo = p.getEmail();
        String novoNome = leString("Alterar nome: "+ nomeAntigo);
        String novoEmail = leString("Alterar email: "+ emailAntigo);
        p.setNome(novoNome);
        p.setEmail(novoEmail);
        PessoaDAO dao = new PessoaDAO();
        dao.update(p);
    }
    public static void metodoUpdateVeiculo(Produto produto) {
    	String numAntigo = produto.getNumeroChassi();
        String placaAntiga = produto.getPlaca();
        String modeloAntigo = produto.getModelo();
        String nomeAntigo = produto.getNome();
        double valorAntigo = produto.getValor();
        String novoNum = leString("Alterar numero Chassi: "+ numAntigo);
        String novaPlaca = leString("Alterar placa: "+ placaAntiga);
        String novoModel = leString("Alterar modelo: "+ modeloAntigo);
        String novoNome = leString("Alterar nome: "+ nomeAntigo);
        String valor = Double.toString(valorAntigo);
        String novoValor = leString("Alterar valor: "+valor);
        double valorNovo = Double.parseDouble(novoValor);
        produto.setNumeroChassi(novoNum);
        produto.setPlaca(novaPlaca);
        produto.setModelo(novoModel);
        produto.setNome(novoNome);
        produto.setValor(valorNovo);
        ProdutoDAO dao = new ProdutoDAO();
        dao.atualizar(produto);
    	
    }
    
    public static Pessoa metodoConsultaId() {
    	String idStr = leString("Digite id");
    	int id = Integer.parseInt(idStr);
    	PessoaDAO dao = new PessoaDAO();
    	Pessoa p = dao.consultarId(id);
    	return p;
    }
    public static Produto metodoConsultaChassi() {
    	String numChassi = leString("Digite id");
    	ProdutoDAO dao = new ProdutoDAO();
    	Produto produto = dao.consultarId(numChassi);
    	return produto;
    }
    public static void metodoListarId() {
    	String numeroChassi = leString("Digite o numero Chassi");
    	ProdutoDAO dao = new ProdutoDAO();
    	Produto p = dao.consultarId(numeroChassi);
    	String saida;
    	if (p != null) {
            saida = "numero\t placa\t \tmodelo \tnome \tvalor\n";
            saida += p.getNumeroChassi() + "\t";
            saida = saida + p.getPlaca() + "\t";
            saida += "\t" + p.getModelo() + "\t";
            saida += "\t" + p.getNome() + "\t";
            saida +="\t" + p.getValor() + "\t\n";
        } else {
            saida = "numero nao encontrado";
        }
        JOptionPane.showMessageDialog(null, new JTextArea(saida));
    }

    public static void metodoConsultarTodos() {
        // Metodo que percorre a lista retornada e exibe os registros
        List<Pessoa> registros = new PessoaDAO().consultaTodos();
        if (!registros.isEmpty()) {
            String saida = "";
            saida += "id\t nome\t \temail\n";
            for (int i = 0; i < registros.size(); i++) {
                Pessoa p = registros.get(i);
                saida += p.getId() + "\t";
                saida = saida + p.getNome() + "\t";
                saida += "\t" + p.getEmail() + "\t\n";
            }
            JOptionPane.showMessageDialog(null, new JTextArea(saida));
        } else {
            System.out.println("Não tem registro.");
        }
    }

    public static void metodoDeletar() {
        String idStr = leString("Digite o ID que queira deletar:");
        int id = Integer.parseInt(idStr);
        PessoaDAO dao = new PessoaDAO();
        String saida;
        if (dao.deletar(id)) {
            saida = ("excluido com sucesso");
        } else {
            saida = "registro nao foi excluido";
        }
        JOptionPane.showMessageDialog(null, new JTextArea(saida));
    }
    public static void metodoDeletarVeiculo() {
    	String n = leString("Digite o Numero Chassi que deseja deletar");
    	ProdutoDAO dao = new ProdutoDAO();
    	String saida;
    	if (dao.excluir(n)) {
    		saida = ("excluido com sucesso");
    	}else {
    		saida = "registro não foi excluido ou não encontrado";
    	}
    	JOptionPane.showMessageDialog(null, new JTextArea(saida));
    }

    public static void main(String[] args) {
        int op;
        do {
            op = menu();
            switch (op) {
                case 1:
                    metodoInserir();
                    break;
                case 2:
                    metodoConsultarTodos();
                    break;
                case 3:
                	Pessoa pes = metodoConsultaId();
                    //String idStr = leString("Digite o ID:");
                    //int id = Integer.parseInt(idStr);
                    //PessoaDAO dao = new PessoaDAO();
                    //Pessoa pes = dao.consultarId(id);
                    String saida;
                    if (pes != null) {
                        saida = "id\t nome\t \temail\n";
                        saida += pes.getId() + "\t";
                        saida = saida + pes.getNome() + "\t";
                        saida += "\t" + pes.getEmail() + "\t\n";
                    } else {
                        saida = "registro nao encontrado";
                    }
                    JOptionPane.showMessageDialog(null, new JTextArea(saida));
                    break;

                case 4:
                    metodoDeletar();
                    break;
                case 5:
                    Pessoa p = metodoConsultaId();
                    if(p !=null) {
                    	metodoUpdate(p);
                    }else {
                    	System.out.println("registro nao encontrado");
                    }
                    break;
                    
                case 6:
                	metodoInserirVeiculo();
                	break;
                case 7:
                	metodoListarId();
                	break;
                case 8:
                	metodoDeletarVeiculo();
                	break;
                case 9:
                	Produto produto = metodoConsultaChassi();
                	if(produto !=null) {
                		metodoUpdateVeiculo(produto);
                	}else {
                		System.out.println("registro nao encontrado");
                	}
                	break;
                case 10:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        } while (op != 10);
    }
}