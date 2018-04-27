package br.ufpe.cin.if688.visitor;

import br.ufpe.cin.if688.ast.AssignStm;
import br.ufpe.cin.if688.ast.CompoundStm;
import br.ufpe.cin.if688.ast.EseqExp;
import br.ufpe.cin.if688.ast.Exp;
import br.ufpe.cin.if688.ast.ExpList;
import br.ufpe.cin.if688.ast.IdExp;
import br.ufpe.cin.if688.ast.LastExpList;
import br.ufpe.cin.if688.ast.NumExp;
import br.ufpe.cin.if688.ast.OpExp;
import br.ufpe.cin.if688.ast.PairExpList;
import br.ufpe.cin.if688.ast.PrintStm;
import br.ufpe.cin.if688.ast.Stm;
import br.ufpe.cin.if688.symboltable.IntAndTable;
import br.ufpe.cin.if688.symboltable.Table;

public class Interpreter implements IVisitor<Table> {
	
	//a=8;b=80;a=7;
	// a->7 ==> b->80 ==> a->8 ==> NIL
	private Table t;
	
	public Interpreter(Table t) {
		this.t = t;
	}

	@Override
	public Table visit(Stm s) {
		// TODO Auto-generated method stub
		return s.accept(this);
	}

	@Override
	public Table visit(AssignStm s) {
		String ids = s.getId();
		Exp e = s.getExp();
		//salvando a tabela antiga na tail dessa
		Table returningTable = new Table(ids, e.accept(this).value, t);
		t = returningTable;
		return t;
	}

	@Override
	public Table visit(CompoundStm s) {
		//da handle nos dois statments
		Stm s1 = s.getStm1();
		Stm s2 = s.getStm2();
		s1.accept(this);
		s2.accept(this);
		return t;
	}

	@Override
	public Table visit(PrintStm s) {
		//passa para a lista dar handle
		return s.getExps().accept(this);
	}

	@Override
	public Table visit(Exp e) {
		// TODO Auto-generated method stub
		return e.accept(this);
	}

	@Override
	public Table visit(EseqExp e) {
		// TODO Auto-generated method stub
		e.getStm().accept(this);
		e.getExp().accept(this);
		Table returningTable = new Table("", e.getExp().accept(this).value, null);
		return returningTable;
	}

	@Override
	public Table visit(IdExp e) {
		
		Table percorre = t;
		String searchingId = e.getId();
		while(percorre.id != searchingId ) {
			//percorre a table para achar a Id referida acima
			percorre = t.tail;
			if(percorre == null){
				System.out.println("Erro: usagem de varíavel não declarada");
				return t;
			}
		}

		//se sair do while, achou a ID
		Table returningTable = new Table("", percorre.value, null);
		return returningTable;
	}

	@Override
	public Table visit(NumExp e) {
		// TODO Auto-generated method stub
		Table returningTable = new Table("", e.getNum(), null);
		return returningTable;
	}

	@Override
	public Table visit(OpExp e) {
		// TODO Auto-generated method stub
		if(e.getOper() == e.Plus) {
			Table returningTable = new Table("", e.getRight().accept(this).value + e.getLeft().accept(this).value, null);
			return returningTable;
		}
		if(e.getOper() == e.Minus) {
			Table returningTable = new Table("", e.getLeft().accept(this).value - e.getRight().accept(this).value, null);
			return returningTable;
		}
		if(e.getOper() == e.Times) {
			Table returningTable = new Table("", e.getRight().accept(this).value * e.getLeft().accept(this).value, null);
			return returningTable;
		}
		if(e.getOper() == e.Div) {
			Table returningTable = new Table("", e.getLeft().accept(this).value / e.getRight().accept(this).value, null);
			return returningTable;
		}
		else{
			System.out.println("Algo deu errado, OpExp deveria ser 1, 2 , 3 ou 4");
			return null;
		}
		
	}

	@Override
	public Table visit(ExpList el) {
		// TODO Auto-generated method stub
		return el.accept(this);
	}

	@Override
	public Table visit(PairExpList el) {
		//converte para float e trata o head
		float tobePrinted =  (float) el.getHead().accept(this).value;
		System.out.printf("%.1f\n",tobePrinted);
		//trata a tail da lista
		el.getTail().accept(this);
		return t;
	}

	@Override
	public Table visit(LastExpList el) {
		// TODO Auto-generated method stub
		float tobePrinted =  (float) el.getHead().accept(this).value;
		System.out.printf("%.1f\n",tobePrinted);
		return t;
	}


}
