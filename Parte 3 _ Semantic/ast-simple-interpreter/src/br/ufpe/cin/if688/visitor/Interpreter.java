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
		// TODO Auto-generated method stub
		Table nt = new Table(s.getId(), s.getExp().accept(this).value, t);
		t = nt;
		return t;
	}

	@Override
	public Table visit(CompoundStm s) {
		// TODO Auto-generated method stub
		s.getStm1().accept(this);
		s.getStm2().accept(this);
		return t;
	}

	@Override
	public Table visit(PrintStm s) {
		return s.getExps().accept(this);
	}

	@Override
	public Table visit(Exp e) {
		// TODO Auto-generated method stub
		IntAndTable iat;
		return e.accept(this);
	}

	@Override
	public Table visit(EseqExp e) {
		// TODO Auto-generated method stub
		e.getStm().accept(this);
		e.getExp().accept(this);
		Table nt = new Table("", e.getExp().accept(this).value, null);
		return nt;
	}

	@Override
	public Table visit(IdExp e) {
		// TODO Auto-generated method stub
		Table percorre = t;
		while(percorre.id != e.getId() ) {
			percorre = t.tail;
		}
		Table nt = new Table("", percorre.value, null);
		return nt;
	}

	@Override
	public Table visit(NumExp e) {
		// TODO Auto-generated method stub
		Table nt = new Table("", e.getNum(), null);
		return nt;
	}

	@Override
	public Table visit(OpExp e) {
		// TODO Auto-generated method stub
		if(e.getOper() == 1) {
			Table nt = new Table("", e.getRight().accept(this).value + e.getLeft().accept(this).value, null);
			return nt;
		}
		if(e.getOper() == 2) {
			Table nt = new Table("", e.getLeft().accept(this).value - e.getRight().accept(this).value, null);
			return nt;
		}
		if(e.getOper() == 3) {
			Table nt = new Table("", e.getRight().accept(this).value * e.getLeft().accept(this).value, null);
			return nt;
		}
		if(e.getOper() == 4) {
			Table nt = new Table("", e.getLeft().accept(this).value / e.getRight().accept(this).value, null);
			return nt;
		}
		
		return null;
	}

	@Override
	public Table visit(ExpList el) {
		// TODO Auto-generated method stub
		return el.accept(this);
	}

	@Override
	public Table visit(PairExpList el) {
		double i =  (double) el.getHead().accept(this).value;
		System.out.printf("%.1f\n",i);
		el.getTail().accept(this);
		return t;
	}

	@Override
	public Table visit(LastExpList el) {
		// TODO Auto-generated method stub
		double i =  (double) el.getHead().accept(this).value;
		System.out.printf("%.1f\n",i);
		return t;
	}


}
