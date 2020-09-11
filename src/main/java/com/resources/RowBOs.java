package com.resources;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class RowBOs {
	
	Row row;
	List<Cell> cells;
	
	public List<Cell> initializeCells(){
		cells = new ArrayList<Cell>();
		return cells;
	}
	
	public Row getRow() {
		return row;
	}
	public void setRow(Row row) {
		this.row = row;
	}
	public List<Cell> getCells() {
		return cells;
	}
	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}
}
