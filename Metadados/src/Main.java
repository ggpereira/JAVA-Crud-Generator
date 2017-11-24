import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
public class Main 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		
		Collection<Tabela> t = new ArrayList<Tabela>();
		Metadados m = new Metadados();
		
		t = m.tablesMetadata();
		
		for(Tabela table : t)
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
