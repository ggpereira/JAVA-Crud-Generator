import java.util.Collection; 
import java.util.ArrayList; 


public class GeraMetodosDAO 
{
	
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
	

	
}
