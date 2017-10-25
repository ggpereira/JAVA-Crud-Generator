import java.util.Collection; 
import java.util.ArrayList;


public class Main 
{
	public static void main(String[] args)
	{
		
		
		Metadados m = new Metadados();
		Collection<Tabela> t = new ArrayList<Tabela>();
		GeraSQL sql = new GeraSQL();
		
		t = m.getTables(); 
		m.getColumnsMetadata(t);
		m.printGeneralMetadata();
		
		for(Tabela table : t)
		{
			System.out.println(table.getTableName().toUpperCase());
			for(Coluna column : table.getColumns())
			{
				System.out.println(column.getColumnName() + " " + column.getTypeName() + " " + column.getSize());
			}
			System.out.println(sql.createInsert(table));
			
			System.out.println("\n");
		}
				
	}
}
