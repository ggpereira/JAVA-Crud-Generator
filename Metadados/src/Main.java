import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

/*T2 - PARADIGMAS DE PROGRAMAÇÃO - GERADOR DE CRUD
Nomes:Gabriel Gomes Pereira, Otávio da Cruz Mello
Turma:Sistemas de Informação 
*/

public class Main 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		/*Lista de tabelas encontradas no banco de dados*/
		Collection<Tabela> t = new ArrayList<Tabela>();
		Metadados m = new Metadados();
		
		/*Recebe as tabelas preenchidas*/
		t = m.tablesMetadata();
		
		for(Tabela table : t) /*Escreve as classes no arquivo*/
		{
			GeraClasse c1 = new GeraClasse();
            		GeraMetodosDAO c2 = new GeraMetodosDAO();
            		GeraExemplo c3 = new GeraExemplo();
           	 	PrintWriter out = new PrintWriter("src/" + table.getTableName().substring(0,1).toUpperCase() + table.getTableName().substring(1) + ".java");
			out.println(c1.geraClasse(table));
            		out.close();
            		PrintWriter out2 = new PrintWriter("src/" + table.getTableName().substring(0,1).toUpperCase() + table.getTableName().substring(1) + "DAO.java");
            		out2.println(c2.geraMetodosDAO(table));
            		out2.close();
           		PrintWriter out3 = new PrintWriter("src/" + table.getTableName().substring(0,1).toUpperCase() + table.getTableName().substring(1) + "Exemplo.java");
            		out3.println(c3.geraExemplo(table));
           	 	out3.close();
		}
		
	}
}
