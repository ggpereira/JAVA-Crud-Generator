import java.util.Collection; 
import java.util.ArrayList; 


public class GeraMetodosDAO 
{
	private String metodoInserir; 
	private String metodoDeletar;
	private String metodoBuscar;
	private String metodoAlterar;
	
	public Collection<String> geraResultSet(Tabela t)
	{
		Collection<String> result_set = new ArrayList<String>();
		String op;
		
		for(Coluna column  : t.getColumns())
		{
			op = "rs.get" + column.getTypeName();
			System.out.println(op);
			result_set.add(op);
		}
	
		
		return result_set;
	}
	
	
	public String geraInsert(Tabela t)
	{
		GeraSQL sql = new GeraSQL();
		metodoInserir = "public void inserir(" + t.getTableName() + " t) {\n";
		metodoInserir += "\tConnection conexao = open();\n\n";
		metodoInserir += "\ttry {\n\t\tPreparedStatement ps = conexao.prepareStatement(\"" + sql.createInsert(t) + "\" )";
		
		
		
		return metodoInserir;
	}
	
	
	
	

}
