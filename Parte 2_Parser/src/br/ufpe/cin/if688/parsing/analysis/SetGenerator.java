package br.ufpe.cin.if688.parsing.analysis;

import java.util.*;

import br.ufpe.cin.if688.parsing.grammar.*;


public final class SetGenerator {
    
    public static Map<Nonterminal, Set<GeneralSymbol>> getFirst(Grammar g) {
        
    	if (g == null) throw new NullPointerException("g nao pode ser nula.");
        
    	Map<Nonterminal, Set<GeneralSymbol>> first = initializeNonterminalMapping(g);
    	/*
    	 * Implemente aqui o método para retornar o conjunto first
    	 */
    	////System.out.println(g);
    	Set<GeneralSymbol> firsts = new HashSet<GeneralSymbol>();
    	for(int i =0;i<5;i++) {
    	for(Production element : g.getProductions()) {
    		//se o primeiro elemento for um terminal
    		if(!isNonTerminal(element.getProduction().get(0))) {
    		//	//System.out.println("O primeiro elemento "+ element.getProduction().get(0) + " é um não terminal, logo, o first de " + element.getNonterminal() + " é apenas ele");
    			firsts.addAll(first.get(element.getNonterminal()));
    			firsts.add(element.getProduction().get(0));
    			first.put(element.getNonterminal(), firsts);
    			////System.out.println("Agora os firsts dele são " + firsts);
    			firsts = new HashSet<GeneralSymbol>();
    		}
    		
    		//se o primeiro elemento for um não terminal
    		
    		if(isNonTerminal(element.getProduction().get(0))) {
    			
    			//se não tem epson nesse não terminal
    			if(!first.get(element.getProduction().get(0)).toString().contains(SpecialSymbol.EPSILON.toString())){
    				////System.out.println("O primeiro elemento "+ element.getProduction().get(0) + " é um terminal, e nele não tem epson, logo, o first de " + element.getNonterminal() + " é o first dele");
        			firsts.addAll(first.get(element.getNonterminal()));
        			for(Production subElement : g.getProductions()) {
        				if(subElement.getNonterminal().toString().equals(element.getProduction().get(0).toString())) {
        					firsts.addAll(first.get(subElement.getNonterminal()));
        				}
        			}
        			first.put(element.getNonterminal(), firsts);
        			////System.out.println("Agora os firsts dele são : " + firsts);
        			firsts = new HashSet<GeneralSymbol>();
    			}
    			
    			
    			//se tem epson nesse não terminal
    			if(first.get(element.getProduction().get(0)).toString().contains(SpecialSymbol.EPSILON.toString())){
    				////System.out.println("O primeiro elemento "+ element.getProduction().get(0) + " é um terminal, e nele tem epson, logo, o first de " + element.getNonterminal() + " é o first dele, mais o first do proximo");
    				//enqnt o proximo elemento for um não terminal com epson, vai pegando os firsts
    				firsts.addAll(first.get(element.getNonterminal()));
    				
    				for(int j = 0;first.get(element.getProduction().get(j)).contains(SpecialSymbol.EPSILON);j++ ) {
    					////System.out.println("O proximo elemento" + element.getProduction().get(j) + "Tambem em epson, então adiciona o first dele tambem");
    					GeneralSymbol nonTerminalSymbol = element.getProduction().get(j);
    					for(Production subElement : g.getProductions()) {
            				if(subElement.getNonterminal().toString().equals(nonTerminalSymbol.toString())) {
            					
            					firsts.addAll(first.get(subElement.getNonterminal()));
            				}
            			}
    					GeneralSymbol nonTerminalSymbolNext = element.getProduction().get(j+1);
    					for(Production subElement : g.getProductions()) {
            				if(subElement.getNonterminal().toString().equals(nonTerminalSymbolNext.toString())) {
            					firsts.addAll(first.get(subElement.getNonterminal()));
            				}
            			}
    				}
    				
    				firsts.remove(SpecialSymbol.EPSILON);
    				first.put(element.getNonterminal(), firsts);
        			////System.out.println("Agora os firsts dele são : " + firsts);
        			firsts = new HashSet<GeneralSymbol>();
    			}
    		}
    		
    		//se o primeiro elemente for epson
    		
    	}
    }
    	////System.out.println(first.toString());
        return first;
    }

    
    public static Map<Nonterminal, Set<GeneralSymbol>> getFollow(Grammar g, Map<Nonterminal, Set<GeneralSymbol>> first) {
        
        if (g == null || first == null)
            throw new NullPointerException();
                
        Map<Nonterminal, Set<GeneralSymbol>> follow = initializeNonterminalMapping(g);
        
        /*
         * implemente aqui o método para retornar o conjunto follow
         */
        //System.out.println(g);
        Set<GeneralSymbol> follows = new HashSet<GeneralSymbol>();
        
        ArrayList<Set<GeneralSymbol>> followList = new ArrayList<Set<GeneralSymbol>>();
        //aqui
        boolean startIndicator = true;
        for(int k = 0;k<10;k++) {
	        
	        for(Production element : g.getProductions()) {
	        	//se for o primeiro, adiciona $ no follow
	        	if(startIndicator == true) {
	        		follows.add(SpecialSymbol.EOF);
	        		follow.put(element.getNonterminal(), follows);
	        		startIndicator = false;
	        		follows = new HashSet<GeneralSymbol>();
	        	}
	        	
	     
	        	int i = 0;
	        	for(GeneralSymbol subElement : element.getProduction()) {
	        	
	        		if(isNonTerminal(subElement)) {
	        			//se for o ultimo
	        			if(element.getProduction().get(element.getProduction().size() -1 ).equals(subElement)) {
	        				////System.out.println("Esse n terminal (" + subElement  + ") eh o ultimo elemento da producao, logo, no seu follow ha o follow de " + element.getNonterminal());
	        				follows.addAll(follow.get(element.getNonterminal()));
	        				for(Nonterminal nElement : g.getNonterminals()) {
	        					if(nElement.toString().equals(subElement.toString())) {
	        						follows.addAll(follow.get(nElement));
	        						follow.put(nElement , follows);
	        						////System.out.println("Foi adicionado ao follow de " + nElement + " os seguintes simbolos" + follows);
	        					}
	        				}
	        				follows = new HashSet<GeneralSymbol>();
	        			}	
	        			else {
	        				//se tiver n�oterminal dps
	        				if(isNonTerminal(element.getProduction().get(i+1))){
	        					////System.out.println(" Esse n terminal (" + subElement + ") nao eh o ultimo, e depois dele vem o nao terminal " + element.getProduction().get(i+1));
	        					//se o nonTerminal tem um epson no first
	        					if(first.get(element.getProduction().get(i+1)).contains(SpecialSymbol.EPSILON)) {
	        						////System.out.println("Porem este terminal tem um epson em seu fist, então teremos que pegar seu follow ");
	        						
	        						follows.addAll(first.get(element.getProduction().get(i+1)));
	        						follows.remove(SpecialSymbol.EPSILON);
	        						follows.addAll(follow.get(element.getProduction().get(i+1)));
	        						follows.addAll(follow.get(subElement));
	        						for(Nonterminal nElement : g.getNonterminals()) {
	        							if(nElement.toString().equals(subElement.toString())) {
	        								follow.put(nElement, follows);
	        								////System.out.println("Foi adicionado ao follow de " + nElement + " os seguintes simbolos" + follows);
	        							}
	        						}
	        						
	        						
	        						
	        					}		
	        					//se o non terminal n�o tem um epson no first
	        					if(!first.get(element.getProduction().get(i+1)).contains(SpecialSymbol.EPSILON)) {
	        						follows.addAll(first.get(element.getProduction().get(i+1)));
	        						follows.remove(SpecialSymbol.EPSILON);
	        						follows.addAll(follow.get(subElement));
	        						for(Nonterminal nElement : g.getNonterminals()) {
	        							if(nElement.toString().equals(subElement.toString())) {
	        								follow.put(nElement, follows);
	        								////System.out.println("Foi adicionado ao follow de " + nElement + " os seguintes simbolos" + follows);
	        							}
	        						}
	        					}
	        				}
	        				//se tiver terminal dps
	        				if(!isNonTerminal(element.getProduction().get(i+1))) {
	        					////System.out.println(" Esse nao terminal (" + subElement + ") nao eh o ultimo, e depois dele vem o terminal " + element.getProduction().get(i+1));
	        					follows.add(element.getProduction().get(i+1));
	        					for(Nonterminal nElement : g.getNonterminals()) {
	            					if(nElement.toString().equals(subElement.toString())) {
	            						follows.addAll(follow.get(nElement));
	            						follow.put(nElement , follows);
	            						////System.out.println("Foi adicionado ao follow de " + nElement + " os seguintes simbolos" + follows);
	            					}
	            				}
	        				}
	        				follows = new HashSet<GeneralSymbol>();
	        			}
	        		}
	        		
	        		if(isNonTerminal(subElement)) {
	        			////System.out.println("O follow de " + subElement + " esta " + follow.get(subElement));
	        			////System.out.println(g);
	        		}
	        		
	        		i++;
	        	}
	        } 	
    }
        ////System.out.println(follow);
        return follow;
    }
    
    public static boolean isNonTerminal(GeneralSymbol x) {
    	if(x.toString().startsWith("<")) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    
    //método para inicializar mapeamento nãoterminais -> conjunto de símbolos
    private static Map<Nonterminal, Set<GeneralSymbol>>
    initializeNonterminalMapping(Grammar g) {
    Map<Nonterminal, Set<GeneralSymbol>> result = 
        new HashMap<Nonterminal, Set<GeneralSymbol>>();

    for (Nonterminal nt: g.getNonterminals())
        result.put(nt, new HashSet<GeneralSymbol>());

    return result;
}

} 