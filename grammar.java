package grammar;

import java.util.*;


class Types{
	static Map<String,String> mp;
	
	public static boolean sentence(String[] words) {
		mp = new HashMap<String,String>();
		
		boolean singular = noun_phrase(words[0],words[1]) && verb_phrase(words);

		boolean plural = noun_phrase_plural(words[0],words[1]) && verb_phrase_plural(words);
		
		//System.out.println(noun_phrase(words[0],words[1]));
		//System.out.println( noun_phrase_plural(words[4],words[5]));
		//System.out.println(noun_phrase_plural(words[0],words[1]));
		//System.out.println(verb_phrase_plural(words));
		
		if((singular || plural) == true) {
			System.out.print("sent(noun_phrase(");
			System.out.print(mp.get(words[0]) + "(" + words[0] + ")");
			System.out.print(mp.get(words[1]) + "(" + words[1] + ")");
			System.out.print("),verb_phrase(");
			for(int c=2;c<words.length;c++) {
				System.out.print(mp.get(words[c]) + "(" + words[c] + ")");
			}
			System.out.println("))");
			return true;
		}
		
		return false;
		
	}
	
	public static boolean verb_phrase_plural(String[] words) {

		if(words.length == 3)
			return verb_p(words[2]);

		if(words.length == 4) 
			return verb_p(words[2]) && noun_plural(words[3]);
		

		if(words.length == 5) 
			return (verb_p(words[2]) && (noun_phrase(words[3],words[4]) || noun_phrase_plural(words[3],words[4]))) || (verb_p(words[2]) && det_ex(words[3]) && noun_plural(words[3])) ;
		
		if(!(verb(words[2])))
			return false;
		
		return determiner_EX(words) || determiner_EX_p(words);
	
	}

	public static boolean verb_phrase(String[] words) { // Ignore position 0 and 1
		if(words.length == 3)
			return verb(words[2]);

		if(words.length == 4) 
			return verb(words[2]) && noun(words[3]);
		

		if(words.length == 5 && verb(words[2])) 
			return (noun_phrase(words[3],words[4]) || noun_phrase_plural(words[3],words[4])) || (det_ex(words[3]) && noun(words[4])) ;
		
		if(!(verb(words[2])))
			return false;
		
		return determiner_EX(words) || determiner_EX_p(words);
	
	}
	
	public static boolean determiner_EX(String[] words) {

		if(det_ex(words[3]) && noun_phrase(words[4],words[5]))
			return true;
		return false;
	}
	
	public static boolean determiner_EX_p(String[] words) {
		if(det_ex(words[3]) && noun_phrase_plural(words[4],words[5]))
			return true;
		return false;
	}
	
	public static boolean det_ex(String n) {
		if(n.equalsIgnoreCase("para") || n.equalsIgnoreCase("com")) {
			mp.put(n, "Determiner");
			return true;
		}
		
		return false; 
		
	}
	
	public static boolean verb(String n) {
		if(n.equalsIgnoreCase("bateu") || n.equalsIgnoreCase("corre") || n.equalsIgnoreCase("correu")){
			mp.put(n, "Verb");
			return true;
		}
		return false;
	}
	
	public static boolean verb_p(String n) {
		if(n.equalsIgnoreCase("bateram") || n.equalsIgnoreCase("corriam") || n.equalsIgnoreCase("correm")){
			mp.put(n, "Verb");
			return true;
		}
		return false;
	}
	
	public static boolean noun_phrase(String n1, String n2) {
		
		boolean res = determiner(n1,n2) || (noun(n1) && n2.isEmpty()) || (noun(n2) && n1.isEmpty());
		return res;
	}
	
	public static boolean noun_phrase_plural(String n1, String n2) {
		
		boolean res = determiner_plural(n1,n2) || (noun_plural(n1) && n2.isEmpty()) || (noun_plural(n2) && n1.isEmpty());
		return res;
	}
	
	public static boolean determiner_plural(String n,String n1) {
		
		if(n.equalsIgnoreCase("os") || n.equalsIgnoreCase("nos")) {
			if(n1.equalsIgnoreCase("tambores") || n1.equalsIgnoreCase("lobos")){
				mp.put(n, "Determiner");
				mp.put(n1, "Noun");
				return true;
			}
		}
		if(n.equalsIgnoreCase("As")) {
			if(n1.equalsIgnoreCase("lagrimas")){
				mp.put(n, "Determiner");
				mp.put(n1, "Noun");
				return true;
			}
		}
		return false;
	}
	
	public static boolean noun_plural(String n) {
		
		if(n.equalsIgnoreCase("lagrimas") || n.equalsIgnoreCase("tambores") || n.equalsIgnoreCase("lobos")) {
			mp.put(n, "Noun");
			return true;
		}
		return false;
	}

	public static boolean determiner(String det, String n) {
		
																																																	
		if( det.equals("O") || det.equals("o") || det.equals("pelo") || det.equals("no")) {
			if( n.equals("tempo") || n.equals("vento") || n.equals("cacador") || n.equals("rosto") || n.equals("rio") ||n.equals("mar") || n.equals("martelo") || n.equals("cachorro") || n.equals("tambor") || n.equals("sino")) {
				mp.put(det, "Determiner");
				mp.put(n, "Noun");
				return true;
			}
		}
		
		if( det.equals("A") || det.equals("a") || det.equals("pela") || det.equals("na")) {
			if( n.equals("menina") || n.equals("floresta") || n.equals("mae") || n.equals("vida") || n.equals("noticia") ||n.equals("cidade") || n.equals("porta")) {
				mp.put(det, "Determiner");
				mp.put(n, "Noun");
				return true;
			}
		}
		
		return false;
		
	}
	
	public static boolean noun(String n) {
		if( n.equals("menina") || n.equals("floresta") || n.equals("mae") || n.equals("vida") || n.equals("noticia") ||n.equals("cidade") || n.equals("porta") || n.equals("tempo") || n.equals("vento") || n.equals("cacador") || n.equals("rosto") || n.equals("rio") ||n.equals("mar") || n.equals("martelo") || n.equals("cachorro") || n.equals("tambor") || n.equals("sino")) {
			mp.put(n, "Noun");
			return true;
		}
				
		return false;
		
	}
	
	

}

public class grammar {
	public static void main (String[] args) {
		String in[]; 
		long StartTime;
		long EndTime;
		
		
		
	

		in = new String[] { "A", "menina","corre","para","a","floresta"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
			
		
		in = new String[] { "A", "menina","corre","para","a","mae"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "vida","corre"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "O", "tempo","corre"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "O", "cacador","correu","com","os","lobos"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "noticia","correu","pela","cidade"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "As", "lagrimas","corriam","pelo","rosto"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
			
		
		in = new String[] { "O", "rio","corre","para","o","mar"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "menina","bateu","a","porta"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "porta","bateu"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "O", "vento","bateu","a","porta"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "menina","bateu","na","porta"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "O", "martelo","bateu","na","porta"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] {"A", "menina","bateu","no","cachorro"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] {"A", "menina","bateu","no","tambor"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "Os", "tambores","bateram"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "O", "sino","bateu"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "menina","corre"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] {"A", "vida","correu"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "noticia","correu","para","a","floresta"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "vida","correu","com","os","lobos"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "menina","bateu","a","mae"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "tempo","corre"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] {"O", "tempo","correram"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "cacador","corriam","pela","rosto"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "A", "tambores","corriam","pela","floresta"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "Os", "tambores","bateu","na","porta"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
		
		
		in = new String[] { "O", "sino","bateu","na","meninas"};
		StartTime = System.nanoTime();
		System.out.println(Types.sentence(in));
		EndTime=System.nanoTime();
		System.out.println("Took "+(EndTime - StartTime) + " ms");
		System.out.println();
	}
}