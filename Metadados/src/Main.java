
public class Main {
	public static void main(String[] args)
	{
		Metadados metadados = new Metadados();
		
		metadados.printGeneralMetadata();
		metadados.getColumnsMetadata(metadados.getTables());
	}
}
