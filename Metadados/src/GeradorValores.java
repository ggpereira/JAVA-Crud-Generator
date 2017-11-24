import java.util.Random;


public class GeradorValores {
	/*CLASSE USADA PARA GERAR DADOS ALEAT�RIOS PARA OS TESTES NAS CLASSES DE EXEMPLO*/
	
	
	/*� sorteada uma posi��o de um vetor de palavras
	 * A posi��o sorteada � a palavra que ser� retornada
	 */
	private static String getPalavra()
	{
		Random g = new Random();  /*Gerar n�meros aleat�rios*/

		String palavras[] = {"Batman", "Nicolas Cage", "Morgan Freeman", "Iron Maiden", "Pink Floyd", "Homem Aranha", "Zeus", "Megaman", "Megadrive", "Gandalf", "Hobbit", "Led Zeppelin", "War pigs", "Paranoid", "Iron Man"};
		 
		int index = g.nextInt(8);
		
		return palavras[index]; /*Retorna string aleat�ria*/
	} 
	
	/*Gera e retorna um n�mero aleat�rio para dados num�ricos*/
	private static String getNumero()
	{
		Random g = new Random(); 
		
		return Integer.toString(g.nextInt(300));/*Retorna numero aleat�rio entre 0 e 300*/
	}
	
	/*Sorteia um valor que ser� uma posi��o correspondente a um char na String
	 * O char selecionado � retornado 
	 */
	private static char getChar()
	{
		char letra;
		
		String letras = "abcdefghijklmnopqrstuvxz"; 
		
		Random g = new Random();  /*Gerar n�meros aleat�rios*/
		
		letra = letras.charAt(g.nextInt(23));
		
		return letra; /*Retorna char aleat�rio*/
	}
	
	/*Retorna um valor de acordo com o tipo solicitado*/
	public static String getValor(String tipoDado) 
	{
		switch(tipoDado)
		{
			case "int":
				return getNumero();
			case "String": 
				return "\"" + getPalavra() + "\""; 
			case "float": 
				return getNumero();
			case "double": 
				return getNumero();
			case "long":
				return getNumero();
			case "char":
				return Character.toString(getChar());
			case "boolean" :
				return "true";
			default:
				System.out.println("Tipo n�o identificado!");
		}
		
		return null;
	}
	
}
